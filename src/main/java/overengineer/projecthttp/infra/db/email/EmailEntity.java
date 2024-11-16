package overengineer.projecthttp.infra.db.email;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import overengineer.projecthttp.infra.rabbitmq.Email;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String email;
    private String subject;
    private String body;
    private LocalDateTime sentAt;
    private LocalDateTime receivedAt;
    private Status status;

    public EmailEntity(Email email) {
        this.id = email.getId().toString();
        this.email = email.getEmailTo();
        this.subject = email.getSubject();
        this.body = email.getBody();
    }

    public void markAsSentQueue() {
        this.status = Status.SENT_QUEUE;
        this.sentAt = LocalDateTime.now();
    }

    public void markAsReceivedQueue() {
        this.status = Status.RECEIVED_QUEUE;
        this.receivedAt = LocalDateTime.now();
    }

    public void markAsFailed() {
        this.status = Status.FAILED;
    }

}
