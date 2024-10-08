package restapi.rapaz.infra.use_case.crud.pessoa;

import java.util.UUID;

public record PersonFound(UUID id, String nome, String sobrenome, Integer idade, String email) {
}
