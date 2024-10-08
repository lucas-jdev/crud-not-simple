package restapi.rapaz.infra.use_case.crud.pessoa;

import java.util.UUID;

public record PersonUpdated(UUID id, String nome, String email, String sobrenome, Integer idade) {
}
