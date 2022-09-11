package totalshake.ciandt.com.apipedido.domain.service.state.impl;

import totalshake.ciandt.com.apipedido.application.errors.CodInternoErroApi;
import totalshake.ciandt.com.apipedido.application.errors.exceptions.StatusInvalidoException;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import totalshake.ciandt.com.apipedido.domain.service.state.EstadoPedido;

public class SaiuParaEntregaImpl implements EstadoPedido {

    private final Pedido pedido;
    private final Status saiuParaEntrega = Status.SAIU_PARA_ENTREGA;

    public SaiuParaEntregaImpl(Pedido pedido) {
        this.pedido = pedido;
        this.pedido.setStatus(saiuParaEntrega);
    }

    @Override
    public void pedidoEntregue() {
        this.pedido.setEstadoPedido(new EntregueImpl(this.pedido));
    }

    @Override
    public void realizarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                saiuParaEntrega
        );
    }

    @Override
    public void pagarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                saiuParaEntrega
        );
    }

    @Override
    public void confirmarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                saiuParaEntrega
        );
    }

    @Override
    public void cancelarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                saiuParaEntrega
        );
    }

    @Override
    public void pedidoPronto() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                saiuParaEntrega
        );
    }

    @Override
    public void pedidoSaiuParaEntrega() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                saiuParaEntrega
        );
    }

    @Override
    public void naoAutorizarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                saiuParaEntrega
        );
    }
}
