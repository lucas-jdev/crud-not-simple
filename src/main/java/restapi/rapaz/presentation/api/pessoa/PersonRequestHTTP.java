package restapi.rapaz.presentation.api.pessoa;

public record PersonRequestHTTP(
    String nome,
    String sobrenome,
    Integer idade,
    String email
) {
}
