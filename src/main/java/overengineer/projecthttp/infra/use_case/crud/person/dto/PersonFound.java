package overengineer.projecthttp.infra.use_case.crud.person.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PersonFound(
    UUID id,
    String name,
    String lastName,
    LocalDate birthDate,
    String email,
    Boolean active) {
}
