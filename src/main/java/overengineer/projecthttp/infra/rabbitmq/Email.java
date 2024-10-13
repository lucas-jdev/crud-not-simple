package overengineer.projecthttp.infra.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable {

    private UUID id;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String body;
    private LocalDateTime sentAt;

}
