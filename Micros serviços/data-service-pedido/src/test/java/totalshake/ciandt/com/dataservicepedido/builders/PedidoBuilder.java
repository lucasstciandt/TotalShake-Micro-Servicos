package totalshake.ciandt.com.dataservicepedido.builders;


import totalshake.ciandt.com.dataservicepedido.domain.model.ItemPedido;
import totalshake.ciandt.com.dataservicepedido.domain.model.Pedido;
import totalshake.ciandt.com.dataservicepedido.domain.model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PedidoBuilder {

    private Pedido pedido;

    private PedidoBuilder(){

    }

    public static PedidoBuilder umPedido(){
        var pedidoBuilder = new PedidoBuilder();
        pedidoBuilder.pedido = new Pedido();
        pedidoBuilder.pedido.setUuidCliente(UUID.randomUUID());
        pedidoBuilder.pedido.setUuidRestaurante(UUID.randomUUID());
        pedidoBuilder.pedido.setTotal(new BigDecimal("67.00"));
        pedidoBuilder.pedido.registrarCriacao();

        return pedidoBuilder;
    }

    public PedidoBuilder comUmItemPedido() {
        var itemPedido =  new ItemPedido();
        itemPedido.setDescricao("Bolacha");
        itemPedido.setQuantidade(2);

        pedido.adicionarItem(itemPedido);
        return this;
    }

    public Pedido build(){
        return this.pedido;
    }
}
