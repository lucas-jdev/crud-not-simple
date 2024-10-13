package overengineer.projecthttp.infra.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import overengineer.projecthttp.infra.application_service.messaging.EmailService;
import overengineer.projecthttp.infra.rabbitmq.Email;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void consume(Email email) {
        emailService.sendEmail(email);
        log.info("Email sent to: {}", email.getEmailTo());
    }

}
