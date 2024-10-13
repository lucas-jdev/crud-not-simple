package overengineer.projecthttp.presentation.api.person;

import java.time.LocalDate;

public record PersonRequestHTTP(
    String name,
    String lastName,
    LocalDate birthDate,
    String email
) {
}
