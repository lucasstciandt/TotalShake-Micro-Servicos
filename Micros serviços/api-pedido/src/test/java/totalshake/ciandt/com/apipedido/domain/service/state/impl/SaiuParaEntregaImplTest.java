package totalshake.ciandt.com.apipedido.domain.service.state.impl;

import totalshake.ciandt.com.apipedido.application.errors.exceptions.StatusInvalidoException;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaiuParaEntregaImplTest {

    Pedido pedidoEmEstadoDeSaiuParaEntrega;

    @BeforeEach
    public void setUp(){
        pedidoEmEstadoDeSaiuParaEntrega = new Pedido();
        pedidoEmEstadoDeSaiuParaEntrega.setStatus(Status.SAIU_PARA_ENTREGA);
        pedidoEmEstadoDeSaiuParaEntrega.setEstadoPedido(new SaiuParaEntregaImpl(pedidoEmEstadoDeSaiuParaEntrega));
    }

    @Nested
    class TestesPossivelAlteracaoDeEstado{
        @Test
        void deve_mudarStatusDoPedidoParaEntregue_setandoEstadoParaEntregue() {
            pedidoEmEstadoDeSaiuParaEntrega.getEstadoPedido().pedidoEntregue();

            assertAll(
                    () -> assertTrue(pedidoEmEstadoDeSaiuParaEntrega.getEstadoPedido() instanceof EntregueImpl),
                    () -> assertEquals(Status.ENTREGUE, pedidoEmEstadoDeSaiuParaEntrega.getStatus())
            );
        }
    }

    @Nested
    class TestesExcecaoDeAlteracaoImpossivelDeEstado{

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarPagarPedidoQueSaiuParaEntrega() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeSaiuParaEntrega.getEstadoPedido().pagarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarSetarPedidoQueSaiuParaEntrega() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeSaiuParaEntrega.getEstadoPedido().pedidoSaiuParaEntrega()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarCancelarPedidoQueSaiuParaEntrega() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeSaiuParaEntrega.getEstadoPedido().cancelarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarRealizarPedidoQueSaiuParaEntrega() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeSaiuParaEntrega.getEstadoPedido().realizarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarConfirmarPedidoQueSaiuParaEntrega() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeSaiuParaEntrega.getEstadoPedido().confirmarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarSetarPedidoProntoQueSaiuParaEntrega(){
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeSaiuParaEntrega.getEstadoPedido().pedidoPronto()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarNaoAutorizarPedido_QueSaiuParaEntrega() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeSaiuParaEntrega.getEstadoPedido().naoAutorizarPedido()
            );
        }
    }

}