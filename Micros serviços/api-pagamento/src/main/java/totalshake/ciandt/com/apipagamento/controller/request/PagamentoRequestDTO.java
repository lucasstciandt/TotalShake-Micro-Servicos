package totalshake.ciandt.com.apipagamento.controller.request;

import javax.validation.constraints.*;
import java.util.UUID;

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
        public UUID getUuidCliente(){
               return UUID.fromString(this.uuidCliente);
        }

        public UUID getUuidPedido(){
                return UUID.fromString(this.uuidPedido);
        }

        public UUID getUuidRestaurante(){
                return UUID.fromString(this.uuidRestaurante);
        }
}
