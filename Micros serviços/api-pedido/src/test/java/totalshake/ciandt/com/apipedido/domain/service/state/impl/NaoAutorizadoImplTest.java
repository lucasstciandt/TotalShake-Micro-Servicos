package totalshake.ciandt.com.apipedido.domain.service.state.impl;

import totalshake.ciandt.com.apipedido.application.errors.exceptions.StatusInvalidoException;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NaoAutorizadoImplTest {

    Pedido pedidoEmEstadoDeNaoAutorizado;

    @BeforeEach
    public void setUp(){
        pedidoEmEstadoDeNaoAutorizado = new Pedido();
        pedidoEmEstadoDeNaoAutorizado.setStatus(Status.NAO_AUTORIZADO);
        pedidoEmEstadoDeNaoAutorizado.setEstadoPedido(new NaoAutorizadoImpl(pedidoEmEstadoDeNaoAutorizado));
    }

    @Nested
    class TestesExcecaoDeAlteracaoImpossivelDeEstado{

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarPagarPedidoNaoAutorizado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeNaoAutorizado.getEstadoPedido().pagarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarSetarEntregueEmPedidoNaoAutorizado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeNaoAutorizado.getEstadoPedido().pedidoEntregue()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_setarPedidoNaoAutorizado_ParaEstadoParaEntregue() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeNaoAutorizado.getEstadoPedido().pedidoSaiuParaEntrega()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarCancelarPedidoNaoAutorizado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeNaoAutorizado.getEstadoPedido().cancelarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarRealizarPedidoNaoAutorizado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeNaoAutorizado.getEstadoPedido().realizarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarConfirmarPedidoNaoAutorizado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeNaoAutorizado.getEstadoPedido().confirmarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarSetarPedidoQueSaiuParaEntrega_emPedidoNaoAutorizado(){
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeNaoAutorizado.getEstadoPedido().pedidoPronto()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarNaoAutorizarPedido_emPedidoNaoAutorizado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeNaoAutorizado.getEstadoPedido().naoAutorizarPedido()
            );
        }
    }

}