package overengineer.projecthttp.presentation.api.person;

import java.util.UUID;

public record PersonResponseHTTP(
    UUID id,
    String nome,
    String sobrenome,
    Integer idade,
    String email,
    Boolean ativo
){
}
