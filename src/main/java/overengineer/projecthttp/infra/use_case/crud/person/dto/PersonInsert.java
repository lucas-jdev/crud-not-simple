package overengineer.projecthttp.infra.use_case.crud.person.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonInsert(
        @NotNull(message = "{name.not-null}") String name,
        @NotNull(message = "{lastName.not-null}") String lastName,
        @NotNull(message = "{birthDate.not-null}")LocalDate birthDate,
        @NotNull(message = "{email.not-null}")
    @Email(message = "{email.invalid-format}") String email) {
}
