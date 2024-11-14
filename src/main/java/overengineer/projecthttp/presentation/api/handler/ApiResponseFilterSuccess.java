package overengineer.projecthttp.presentation.api.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import overengineer.projecthttp.infra.exception.ApplicationException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.UnaryOperator;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiResponseFilterSuccess implements WebFilter {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        HttpStatusCode statusCode = originalResponse.getStatusCode();

        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
                return Flux.from(body)
                        .collectList()
                        .flatMap(dataBuffers -> {
                            String responseBody = extractResponseBody(dataBuffers);

                            String response = trataJson(responseBody, value -> {
                                if (!value.startsWith("{")) return value;
                                ApiResponse<Object> apiResponse = createApiResponse(value, statusCode.value());
                                ResponseEntity<ApiResponse<Object>> responseEntity = ResponseEntity.status(statusCode.value()).body(apiResponse);
                                setResponseHeaders(originalResponse);
                                return serializeApiResponse(responseEntity.getBody());
                            });

                            DataBuffer responseBuffer = bufferFactory.wrap(response.getBytes(StandardCharsets.UTF_8));

                            return super.writeWith(Mono.just(responseBuffer));
                        });
            }
        };

        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    private String trataJson(String value, @NonNull UnaryOperator<String> funcao) {
        return funcao.apply(value);
    }

    private String extractResponseBody(List<? extends DataBuffer> dataBuffers) {
        int contentLength = dataBuffers.stream().mapToInt(DataBuffer::readableByteCount).sum();
        byte[] content = new byte[contentLength];
        int pos = 0;

        for (DataBuffer dataBuffer : dataBuffers) {
            int length = dataBuffer.readableByteCount();
            dataBuffer.read(content, pos, length);
            pos += length;
        }

        return new String(content, StandardCharsets.UTF_8);
    }

    private ApiResponse<Object> createApiResponse(String responseBody, int statusCode) {
        Object responseObject;
        try {
            responseObject = objectMapper.readValue(responseBody, Object.class);
            log.info("Response object: {}", responseObject);
        } catch (Exception e) {
            log.error("Error deserializing response", e);
            throw new ApplicationException("Failed to deserialize response", e);
        }

        return new ApiResponse<>(statusCode, null, LocalDateTime.now(), responseObject);
    }

    private String serializeApiResponse(ApiResponse<Object> apiResponse) {
        try {
            return objectMapper.writeValueAsString(apiResponse);
        } catch (Exception e) {
            log.error("Error serializing response", e);
            throw new ApplicationException("Failed to serialize response", e);
        }
    }

    private void setResponseHeaders(ServerHttpResponse response) {
        response.setStatusCode(response.getStatusCode());
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().remove("Content-Length");
    }
}