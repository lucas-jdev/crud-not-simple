package overengineer.projecthttp.doubletests.spies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import overengineer.projecthttp.infra.rabbitmq.Email;

import java.io.IOException;

@Slf4j
public class ListenerRabbitMQ {


    @RabbitListener(
        id="email",
        queues="ms-queue",
        messageConverter = "messageConverter")
    public void consume(@Payload Email message) {
        log.info("Email received from queue: {}", message);
    }

}