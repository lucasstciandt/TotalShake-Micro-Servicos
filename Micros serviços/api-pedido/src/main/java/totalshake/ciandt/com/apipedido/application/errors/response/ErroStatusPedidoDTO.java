package totalshake.ciandt.com.apipedido.application.errors.response;

import totalshake.ciandt.com.apipedido.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErroStatusPedidoDTO  {

    @JsonProperty("statusPedido")
    private final Status statusAtualDoPedido;

    public ErroStatusPedidoDTO(Status statusAtualDoPedido) {
        this.statusAtualDoPedido = statusAtualDoPedido;
    }

    public Status getStatusAtualDoPedido() {
        return statusAtualDoPedido;
    }
}
