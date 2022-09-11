package totalshake.ciandt.com.apipedido.domain.service.state.impl;

import totalshake.ciandt.com.apipedido.application.errors.exceptions.StatusInvalidoException;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntregueImplTest {

    Pedido pedidoEmEstadoDeEntregue;

    @BeforeEach
    public void setUp(){
        pedidoEmEstadoDeEntregue = new Pedido();
        pedidoEmEstadoDeEntregue.setStatus(Status.ENTREGUE);
        pedidoEmEstadoDeEntregue.setEstadoPedido(new EntregueImpl(pedidoEmEstadoDeEntregue));
    }

    @Nested
    class TestesExcecaoDeAlteracaoImpossivelDeEstado{

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarPagarPedidoJaEntregue() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeEntregue.getEstadoPedido().pagarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarSetarEntregueEmPedidoJaEntregue() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeEntregue.getEstadoPedido().pedidoEntregue()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarSetarSaiuParaEntregaEm_PedidoEntregue() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeEntregue.getEstadoPedido().pedidoSaiuParaEntrega()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarCancelarPedidoJaEntregue() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeEntregue.getEstadoPedido().cancelarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarRealizarPedidoEntregue() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeEntregue.getEstadoPedido().realizarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarConfirmarPedidoEntregue() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeEntregue.getEstadoPedido().confirmarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarSetarPedidoQueSaiuParaEntrega_emPedidoEntregue(){
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeEntregue.getEstadoPedido().pedidoPronto()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarNaoAutorizarPedido_emPedidoEntregue() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeEntregue.getEstadoPedido().naoAutorizarPedido()
            );
        }
    }

}