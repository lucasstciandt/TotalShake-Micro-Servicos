package totalshake.ciandt.com.dataservicepedido.application.controller.request.post;

import totalshake.ciandt.com.dataservicepedido.domain.model.ItemPedido;
import totalshake.ciandt.com.dataservicepedido.domain.model.Pedido;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PedidoDTOPostRequest(
        @NotEmpty @NotNull
        @NotBlank @Size(min = 36, max = 36, message = "UUID invalido")
        String uuidRestaurante,

        @NotEmpty @NotNull
        @NotBlank @Size(min = 36, max = 36, message = "UUID invalido")
        String uuidCliente,

        @Positive @NotNull
        BigDecimal total,

        @NotNull @NotEmpty
        List<@Valid ItemPedidoDTOPostRequest> itens
) {
        public Pedido toPedidoModel() {
                var pedido = new Pedido();
                pedido.setUuidRestaurante(UUID.fromString(uuidCliente));
                pedido.setUuidCliente(UUID.fromString(this.uuidCliente));
                pedido.setTotal(this.total);

                List<ItemPedido> itensPedido = itens
                        .stream()
                        .map(ItemPedidoDTOPostRequest::toItemPedidoModel)
                        .toList();

                itensPedido.forEach(pedido::adicionarItem);

                return pedido;
        }
}
