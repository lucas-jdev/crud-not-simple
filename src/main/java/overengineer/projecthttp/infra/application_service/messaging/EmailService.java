package overengineer.projecthttp.infra.application_service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import overengineer.projecthttp.infra.rabbitmq.Email;
import overengineer.projecthttp.infra.use_case.crud.email.SaveEmailAsFailed;
import overengineer.projecthttp.infra.use_case.crud.email.SaveEmailAsReceived;
import overengineer.projecthttp.infra.use_case.crud.email.SaveEmailAsSent;

@Slf4j
@Service
public record EmailService (
    JavaMailSender javaMailSender,
    SaveEmailAsSent saveEmailAsSent,
    SaveEmailAsReceived saveEmailAsReceived,
    SaveEmailAsFailed saveEmailAsFailed
) {

    public void sendEmail(@NonNull Email email) {
        saveEmailAsSent.execute(email);
        log.info("Sending email to: {}", email.getEmailTo());
    }

    public void receiveEmail(@NonNull Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email.getEmailFrom());
        simpleMailMessage.setTo(email.getEmailTo());
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getBody());

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            failEmail(email);
            return;
        }

        saveEmailAsReceived.execute(email.getId().toString());
        log.info("Receiving email from: {}", email.getEmailFrom());
    }

    public void failEmail(@NonNull Email email) {
        saveEmailAsFailed.execute(email.getId().toString());
        log.info("Failing email from: {}", email.getEmailFrom());
    }

}
