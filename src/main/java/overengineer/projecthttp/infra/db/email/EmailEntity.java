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

    public void markAsSent() {
        this.status = Status.SENT;
        this.sentAt = LocalDateTime.now();
    }

    public void markAsReceived() {
        this.status = Status.RECEIVED;
        this.receivedAt = LocalDateTime.now();
    }

}
