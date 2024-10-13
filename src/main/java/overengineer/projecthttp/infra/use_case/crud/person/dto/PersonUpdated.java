package overengineer.projecthttp.infra.use_case.crud.person.dto;

import java.util.UUID;

public record PersonUpdated(
    UUID id,
    String name,
    String email,
    String lastName,
    Integer yearsOld,
    Boolean active) {
}
