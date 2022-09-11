package totalshake.ciandt.com.apipedido.domain.service.state.impl;

import totalshake.ciandt.com.apipedido.application.errors.exceptions.StatusInvalidoException;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProntoImplTest {

    Pedido pedidoEmEstadoDePronto;

    @BeforeEach
    public void setUp(){
        pedidoEmEstadoDePronto = new Pedido();
        pedidoEmEstadoDePronto.setStatus(Status.PRONTO);
        pedidoEmEstadoDePronto.setEstadoPedido(new ProntoImpl(pedidoEmEstadoDePronto));
    }

    @Nested
    class TestesPossivelAlteracaoDeEstado{
        @Test
        void deve_mudarStatusDoPedidoParaSaiuParaEntrega_setandoEstadoParaSaiuParaEntrega() {
            pedidoEmEstadoDePronto.getEstadoPedido().pedidoSaiuParaEntrega();

            assertAll(
                    () -> assertTrue(pedidoEmEstadoDePronto.getEstadoPedido() instanceof SaiuParaEntregaImpl),
                    () -> assertEquals(Status.SAIU_PARA_ENTREGA, pedidoEmEstadoDePronto.getStatus())
            );
        }
    }

    @Nested
    class TestesExcecaoDeAlteracaoImpossivelDeEstado{

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarPagarPedidoPronto() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDePronto.getEstadoPedido().pagarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarCancelarPedidoPronto() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDePronto.getEstadoPedido().cancelarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_tentarRealizarPedidoPronto() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDePronto.getEstadoPedido().realizarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarConfirmarPronto() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDePronto.getEstadoPedido().confirmarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarSetarPedidoPronto_EmEstadoDePronto(){
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDePronto.getEstadoPedido().pedidoPronto()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarSetarPedidoComoEntregue_EmEstadoDePronto() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDePronto.getEstadoPedido().pedidoEntregue()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarNaoAutorizarPedido_EmEstadoDePronto() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDePronto.getEstadoPedido().naoAutorizarPedido()
            );
        }
    }

}