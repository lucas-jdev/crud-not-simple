package overengineer.projecthttp.infra.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import overengineer.projecthttp.infra.application_service.messaging.EmailService;
import overengineer.projecthttp.infra.rabbitmq.Email;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailProducer {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final RabbitTemplate template;
    private final EmailService emailService;

    public void sendEmail(String email) {
        StringBuilder body = new StringBuilder();
        body.append("Congratulations! Your email was received by our system.\n\n");
        body.append("Server: ").append("John Doe BOT").append("\n");

        String subject = "Email received";

        Email emailMessage = new Email();
        emailMessage.setId(UUID.randomUUID());
        emailMessage.setEmailTo(email);
        emailMessage.setBody(body.toString());
        emailMessage.setEmailFrom(emailFrom);
        emailMessage.setSubject(subject);

        template.convertAndSend(exchange, routingKey, emailMessage);
        emailService.sendEmail(emailMessage);
    }
}
