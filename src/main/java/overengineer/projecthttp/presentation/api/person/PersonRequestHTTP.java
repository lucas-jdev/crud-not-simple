package overengineer.projecthttp.presentation.api.person;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public record PersonRequestHTTP(
    String name,
    String lastName,
    LocalDate birthDate,
    String email
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
