package overengineer.projecthttp.infra.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

}
