package overengineer.projecthttp.presentation.api.handler;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public record ApiResponse<T>(
    int status,
    String message,
    LocalDateTime timestamp, T data
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public ApiResponse(int status, String message, LocalDateTime timestamp) {
        this(status, message, timestamp, null);
    }

}
