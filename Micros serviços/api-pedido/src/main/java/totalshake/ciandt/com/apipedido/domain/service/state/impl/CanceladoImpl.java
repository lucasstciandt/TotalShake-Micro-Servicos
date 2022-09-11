package totalshake.ciandt.com.apipedido.domain.service.state.impl;

import totalshake.ciandt.com.apipedido.application.errors.CodInternoErroApi;
import totalshake.ciandt.com.apipedido.application.errors.exceptions.StatusInvalidoException;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import totalshake.ciandt.com.apipedido.domain.service.state.EstadoPedido;

public class CanceladoImpl implements EstadoPedido {

    private Pedido pedido;
    private final Status cancelado = Status.CANCELADO;


    public CanceladoImpl(Pedido pedido) {
        this.pedido = pedido;
        this.pedido.setStatus(cancelado);
    }

    @Override
    public void realizarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                cancelado
        );
    }

    @Override
    public void pagarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                cancelado
        );
    }

    @Override
    public void confirmarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                cancelado
        );
    }

    @Override
    public void cancelarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                cancelado
        );
    }

    @Override
    public void pedidoPronto() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                cancelado
        );
    }

    @Override
    public void pedidoSaiuParaEntrega() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                cancelado
        );
    }

    @Override
    public void pedidoEntregue() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                cancelado
        );
    }

    @Override
    public void naoAutorizarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                cancelado
        );
    }
}
