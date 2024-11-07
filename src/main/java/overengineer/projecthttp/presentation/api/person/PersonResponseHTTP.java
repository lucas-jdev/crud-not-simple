package overengineer.projecthttp.presentation.api.person;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record PersonResponseHTTP(
    UUID id,
    String nome,
    String sobrenome,
    Integer idade,
    String email,
    Boolean ativo
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
