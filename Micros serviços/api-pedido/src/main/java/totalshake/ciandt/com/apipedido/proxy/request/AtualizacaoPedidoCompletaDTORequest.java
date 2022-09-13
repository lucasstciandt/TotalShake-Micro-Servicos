package totalshake.ciandt.com.apipedido.proxy.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import totalshake.ciandt.com.apipedido.domain.model.DataHoraStatusPedido;
import totalshake.ciandt.com.apipedido.domain.model.ItemPedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record AtualizacaoPedidoCompletaDTORequest(

        UUID uuidPedido,

        UUID uuidRestaurante,

        UUID uuidCliente,

        UUID uuidEntregador,

        Status status,

        BigDecimal total,

        @JsonIgnoreProperties("pedido")
        List<ItemPedido> itens,

        @JsonIgnoreProperties("pedido")
        DataHoraStatusPedido dataHoraStatus
) {

}
