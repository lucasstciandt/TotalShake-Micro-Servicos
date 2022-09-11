package totalshake.ciandt.com.dataservicepedido.application.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import totalshake.ciandt.com.dataservicepedido.domain.model.DataHoraStatusPedido;
import totalshake.ciandt.com.dataservicepedido.domain.model.ItemPedido;
import totalshake.ciandt.com.dataservicepedido.domain.model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoDTOResponse(
        UUID uuid_pedido,

        UUID uuid_cliente,

        UUID uuid_restaurante,

        UUID uuid_entregador,

        Status status,

        BigDecimal total,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime ultimaAtualizacao,

        @JsonIgnoreProperties("pedido")
        List<ItemPedido> itens,

        @JsonIgnoreProperties(value = "pedido")
        DataHoraStatusPedido dataHoraStatus
) {
        public PedidoDTOResponse(UUID uuid_pedido) {
                this(uuid_pedido, null, null,
                        null, null, null,
                        null, null, null);
        }

        public PedidoDTOResponse(UUID uuid_pedido, Status status, LocalDateTime ultimaAtualizacao,
                                 DataHoraStatusPedido dataHoraStatusPedido) {
                this(uuid_pedido, null, null, null,
                        status, null, ultimaAtualizacao, null, dataHoraStatusPedido);
        }
}
