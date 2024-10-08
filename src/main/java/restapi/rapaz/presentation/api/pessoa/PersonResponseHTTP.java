package restapi.rapaz.presentation.api.pessoa;

import java.util.UUID;

public record PersonResponseHTTP(
    UUID id,
    String nome,
    String sobrenome,
    Integer idade,
    String email
){
}
