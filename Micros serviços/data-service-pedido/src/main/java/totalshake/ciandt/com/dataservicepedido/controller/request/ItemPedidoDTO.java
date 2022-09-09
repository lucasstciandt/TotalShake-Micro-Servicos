package totalshake.ciandt.com.dataservicepedido.controller.request;

import totalshake.ciandt.com.dataservicepedido.domain.model.ItemPedido;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public record ItemPedidoDTO(
        @NotNull @NotBlank @Size(min = 3, max = 120)
        String descricao,
        @Positive @NotNull
        Integer quantidade
) {
    public ItemPedido toItemPedidoModel() {
        var itemPedido = new ItemPedido();
        itemPedido.setDescricao(this.descricao);
        itemPedido.setQuantidade(this.quantidade);

        return itemPedido;
    }
}
