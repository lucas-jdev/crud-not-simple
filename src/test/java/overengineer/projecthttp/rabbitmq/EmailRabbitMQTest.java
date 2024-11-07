package overengineer.projecthttp.rabbitmq;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import overengineer.projecthttp.doubletests.spies.ListenerRabbitMQ;
import overengineer.projecthttp.doubletests.spies.RabbitMQTestConfig;
import overengineer.projecthttp.infra.rabbitmq.Email;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(classes = {RabbitMQTestConfig.class})
@ActiveProfiles("test")
class EmailRabbitMQTest {

    private static final String DOCKER_IMAGE = "rabbitmq:3.8-management-alpine";

    @Container
    static RabbitMQContainer container = new RabbitMQContainer(DOCKER_IMAGE);

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", container::getHost);
        registry.add("spring.rabbitmq.port", container::getAmqpPort);
        registry.add("spring.rabbitmq.username", container::getAdminUsername);
        registry.add("spring.rabbitmq.password", container::getAdminPassword);
        registry.add("spring.rabbitmq.queue", () -> "ms-queue");
        registry.add("spring.rabbitmq.exchange", () -> "ms-exchange");
        registry.add("spring.rabbitmq.routing-key", () -> "ms-routing");
    }

    private final String exchange = "ms-exchange";

    @Autowired
    private AmqpTemplate template;

    @Autowired
    private RabbitListenerTestHarness harness;

    @Test
    void sendAndReceiveEmail() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        var email = new Email(UUID.randomUUID(), "sender@dev.com", null, null, null, LocalDateTime.now());
        ListenerRabbitMQ listener = harness.getSpy("email");

        doAnswer(invocation -> {
            latch.countDown();
            return null;
        }).when(listener).consume(Mockito.any(Email.class));

        this.template.convertAndSend(exchange, "ms-routing", email);
        boolean await = latch.await(2, TimeUnit.SECONDS);
        assertTrue(await);
        verify(listener).consume(email);
    }

}
