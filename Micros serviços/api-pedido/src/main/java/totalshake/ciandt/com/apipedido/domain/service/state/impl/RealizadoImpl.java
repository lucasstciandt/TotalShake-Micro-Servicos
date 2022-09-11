package totalshake.ciandt.com.apipedido.domain.service.state.impl;

import totalshake.ciandt.com.apipedido.application.errors.CodInternoErroApi;
import totalshake.ciandt.com.apipedido.application.errors.exceptions.StatusInvalidoException;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import totalshake.ciandt.com.apipedido.domain.service.state.EstadoPedido;

public class RealizadoImpl implements EstadoPedido {

    private final Pedido pedido;
    private final Status realizado = Status.REALIZADO;

    public RealizadoImpl(Pedido pedido) {
        this.pedido = pedido;
        this.pedido.setStatus(realizado);
    }

    @Override
    public void pagarPedido() {
        this.pedido.setEstadoPedido(new PagoImpl(this.pedido));
    }

    @Override
    public void cancelarPedido() {
        this.pedido.setEstadoPedido(new CanceladoImpl(this.pedido));
    }

    @Override
    public void confirmarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                realizado
        );
    }

    @Override
    public void realizarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                realizado
        );
    }

    @Override
    public void pedidoPronto() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                realizado
        );
    }

    @Override
    public void pedidoSaiuParaEntrega() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                realizado
        );
    }

    @Override
    public void pedidoEntregue() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                realizado
        );
    }

    @Override
    public void naoAutorizarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                realizado
        );
    }
}
