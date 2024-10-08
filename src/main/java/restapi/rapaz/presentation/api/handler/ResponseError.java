package restapi.rapaz.presentation.api.handler;

import java.time.LocalDateTime;

public record ResponseError(
    String message,
    int status,
    LocalDateTime timestamp
){
}
