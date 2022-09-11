package totalshake.ciandt.com.apipedido.domain.service.state;

import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import totalshake.ciandt.com.apipedido.domain.service.state.impl.*;

public class EstadoPedidoFactory {

    public static EstadoPedido ofStatus(Status status, Pedido pedido){

        EstadoPedido estadoPedido = null;

        switch (status){
            case CRIADO -> estadoPedido = new CriadoImpl(pedido);
            case REALIZADO ->  estadoPedido =new RealizadoImpl(pedido);
            case PAGO -> estadoPedido = new PagoImpl(pedido);
            case CONFIRMADO -> estadoPedido = new ConfirmadoImpl(pedido);
            case NAO_AUTORIZADO -> estadoPedido = new NaoAutorizadoImpl(pedido);
            case PRONTO -> estadoPedido = new ProntoImpl(pedido);
            case SAIU_PARA_ENTREGA -> estadoPedido = new SaiuParaEntregaImpl(pedido);
            case ENTREGUE -> estadoPedido = new EntregueImpl(pedido);
            case CANCELADO -> estadoPedido = new CanceladoImpl(pedido);
        }

        return estadoPedido;
    }
}
