package totalshake.ciandt.com.apipedido.proxy.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import totalshake.ciandt.com.apipedido.domain.model.DataHoraStatusPedido;
import totalshake.ciandt.com.apipedido.domain.model.ItemPedido;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoDTOGetResponse(
        UUID uuidPedido,

        UUID uuidCliente,

        UUID uuidRestaurante,

        UUID uuidEntregador,

        Status status,

        BigDecimal total,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime ultimaAtualizacao,

        @JsonIgnoreProperties("pedido")
        List<ItemPedido> itens,

        @JsonIgnoreProperties("pedido")
        DataHoraStatusPedido dataHoraStatus
) {
        public PedidoDTOGetResponse(UUID uuid_pedido) {
                this(uuid_pedido, null, null,
                        null, null, null,
                        null, null, null);
        }

        public PedidoDTOGetResponse(UUID uuid_pedido, Status status, LocalDateTime ultimaAtualizacao,
                                    DataHoraStatusPedido dataHoraStatusPedido) {
                this(uuid_pedido, null, null, null,
                        status, null, ultimaAtualizacao, null, dataHoraStatusPedido);
        }

        public PedidoDTOGetResponse(UUID uuid_pedido, UUID uuid_cliente, UUID uuid_restaurante, Status status,
                                    LocalDateTime ultimaAtualizacao, List<ItemPedido> itens
        ) {
                this(uuid_pedido, uuid_cliente, uuid_restaurante, null, status
                        , null, ultimaAtualizacao, itens, null);
        }

        public Pedido toPedidoModel() {
                var pedido = new Pedido();
                pedido.setUuidPedido(this.uuidPedido);
                pedido.setUuidCliente(this.uuidCliente);
                pedido.setUuidRestaurante(this.uuidRestaurante);
                pedido.setUuidEntregador(this.uuidEntregador);
                pedido.setStatus(this.status);
                pedido.setUltimaAtualizacao(this.ultimaAtualizacao);
                pedido.setDataHoraStatus(this.dataHoraStatus);
                pedido.setTotal(this.total);
                this.itens.forEach(pedido::adicionarItem);

                return pedido;
        }
}
