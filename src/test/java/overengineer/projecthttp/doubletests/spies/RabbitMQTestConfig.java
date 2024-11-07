package overengineer.projecthttp.doubletests.spies;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonParser;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Slf4j
@TestConfiguration
@RabbitListenerTest
public class RabbitMQTestConfig {

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey = "ms-routing";

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Bean
    public Queue qEmail() {
        return new Queue("ms-queue");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("ms-exchange");
    }

    @Bean
    public Binding biding(Queue qEmail, TopicExchange topicExchange) {
        return new Binding(qEmail.getName(), Binding.DestinationType.QUEUE, topicExchange.getName(), routingKey, null);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost"); // replace with your RabbitMQ host
        log.info("Creating connection factory");
        connectionFactory.setHost(host);
        connectionFactory.setPort(port); // replace with your RabbitMQ port if necessary
        connectionFactory.setUsername(username); // replace with your RabbitMQ username
        connectionFactory.setPassword(password); // replace with your RabbitMQ password
        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        log.info("RabbitMQ connection factory: {}", connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public ListenerRabbitMQ listener() {
        log.info("Creating listener");
        return new ListenerRabbitMQ();
    }

}