package totalshake.ciandt.com.apipedido.domain.service.state;

public interface EstadoPedido {

    void realizarPedido();
    void pagarPedido();
    void confirmarPedido();
    void cancelarPedido();
    void pedidoPronto();
    void pedidoSaiuParaEntrega();
    void pedidoEntregue();
    void naoAutorizarPedido();
}
