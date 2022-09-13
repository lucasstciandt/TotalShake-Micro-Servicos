package totalshake.ciandt.com.apipagamento.controller.request;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public record PagamentoRequestDTO(
        @NotEmpty @NotNull
        @NotBlank @Size(min = 36, max = 36, message = "UUID invalido")
        String uuidPedido,

        @NotEmpty @NotNull
        @NotBlank @Size(min = 36, max = 36, message = "UUID invalido")
        String uuidCliente,

        @NotEmpty @NotNull
        @NotBlank @Size(min = 36, max = 36, message = "UUID invalido")
        String uuidRestaurante
) {
}
