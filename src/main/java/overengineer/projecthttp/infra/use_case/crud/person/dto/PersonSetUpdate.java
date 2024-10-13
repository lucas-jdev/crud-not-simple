package overengineer.projecthttp.infra.use_case.crud.person.dto;

import java.time.LocalDate;

public record PersonSetUpdate(
    String name,
    String email,
    String lastName,
    LocalDate birthDate) {
}
