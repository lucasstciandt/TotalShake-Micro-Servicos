package totalshake.ciandt.com.apipedido.application.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public record ItemPedidoDTO(
        @NotNull @NotBlank @Size(min = 3, max = 120)
        String item,

        @Positive @NotNull
        Integer quantidade
) {
}
