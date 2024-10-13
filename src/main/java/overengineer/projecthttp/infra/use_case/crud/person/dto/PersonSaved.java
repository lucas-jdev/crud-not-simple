package overengineer.projecthttp.infra.use_case.crud.person.dto;

import java.util.UUID;

public record PersonSaved(
    UUID id,
    String name,
    String email,
    String lastName,
    Integer yearsOld) {
}
