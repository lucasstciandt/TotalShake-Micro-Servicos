package totalshake.ciandt.com.dataservicepedido.application.controller.request.put;

import totalshake.ciandt.com.dataservicepedido.domain.model.DataHoraStatusPedido;
import totalshake.ciandt.com.dataservicepedido.domain.model.ItemPedido;
import totalshake.ciandt.com.dataservicepedido.domain.model.Pedido;
import totalshake.ciandt.com.dataservicepedido.domain.model.Status;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record AtualizacaoCompletaPedidoDTORequest(

        @NotEmpty @NotNull
        @NotBlank @Size(min = 36, max = 36, message = "UUID invalido")
        String uuidPedido,

        @NotEmpty @NotNull
        @NotBlank @Size(min = 36, max = 36, message = "UUID invalido")
        String uuidRestaurante,

        @NotEmpty @NotNull
        @NotBlank @Size(min = 36, max = 36, message = "UUID invalido")
        String uuidCliente,

        String uuidEntregador,

        @NotNull
        Status status,

        @Positive @NotNull
        BigDecimal total,

        @NotNull @NotEmpty
        List<ItemPedido> itens,

        DataHoraStatusPedido dataHoraStatus
) {
        public Pedido toPedidoModel() {
                var pedido = new Pedido();
                pedido.setUuidPedido(UUID.fromString(this.uuidPedido));
                pedido.setUuidCliente(UUID.fromString(this.uuidCliente));
                pedido.setUuidRestaurante(UUID.fromString(this.uuidRestaurante));
                if(uuidEntregador != null){
                        pedido.setUuidEntregador(UUID.fromString(this.uuidEntregador));
                }
                pedido.setStatus(this.status);
                pedido.setDataHoraStatus(this.dataHoraStatus);
                pedido.setTotal(this.total);
                this.itens.forEach(pedido::adicionarItem);

                return pedido;
        }
}
