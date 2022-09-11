package totalshake.ciandt.com.apipedido.domain.service.state.impl;

import totalshake.ciandt.com.apipedido.application.errors.CodInternoErroApi;
import totalshake.ciandt.com.apipedido.application.errors.exceptions.StatusInvalidoException;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import totalshake.ciandt.com.apipedido.domain.service.state.EstadoPedido;

public class PagoImpl implements EstadoPedido {

    private Pedido pedido;
    private final Status pago = Status.PAGO;

    public PagoImpl(Pedido pedido) {
        this.pedido = pedido;
        this.pedido.setStatus(pago);
    }

    @Override
    public void confirmarPedido() {
        this.pedido.setEstadoPedido(new ConfirmadoImpl(this.pedido));
    }

    @Override
    public void naoAutorizarPedido() {
        this.pedido.setEstadoPedido(new NaoAutorizadoImpl(this.pedido));
    }

    @Override
    public void realizarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                pago
        );
    }

    @Override
    public void pagarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                pago
        );
    }

    @Override
    public void cancelarPedido() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                pago
        );
    }

    @Override
    public void pedidoPronto() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                pago
        );
    }

    @Override
    public void pedidoSaiuParaEntrega() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                pago
        );
    }

    @Override
    public void pedidoEntregue() {
        throw new StatusInvalidoException(
                CodInternoErroApi.AP301.getMensagem(),
                CodInternoErroApi.AP301.getCodigo(),
                pago
        );
    }
}
