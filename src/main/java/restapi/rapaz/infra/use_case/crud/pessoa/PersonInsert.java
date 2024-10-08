package restapi.rapaz.infra.use_case.crud.pessoa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PersonInsert(
    @NotNull(message = "{nome.not-null}") String nome,
    @NotNull(message = "{sobrenome.not-null}") String sobrenome,
    @NotNull(message = "{idade.not-null}") Integer idade,
    @NotNull(message = "{email.not-null}")
    @Email(message = "{email.invalid-format}") String email) {
}
