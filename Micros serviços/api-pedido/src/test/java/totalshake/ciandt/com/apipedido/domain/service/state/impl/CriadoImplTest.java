package totalshake.ciandt.com.apipedido.domain.service.state.impl;

import totalshake.ciandt.com.apipedido.application.errors.exceptions.StatusInvalidoException;
import totalshake.ciandt.com.apipedido.domain.model.Pedido;
import totalshake.ciandt.com.apipedido.domain.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CriadoImplTest {
    Pedido pedidoEmEstadoDeCriado;

    @BeforeEach
    public void setUp(){
        pedidoEmEstadoDeCriado = new Pedido();
        pedidoEmEstadoDeCriado.setStatus(Status.CRIADO);
        pedidoEmEstadoDeCriado.setEstadoPedido(new CriadoImpl(pedidoEmEstadoDeCriado));
    }

    @Nested
    class TestesPossivelAlteracaoDeEstado{

        @Test
        void deve_realizarPedido_mudandoOStatusParaRealizado_setandoEstadoParaRealizado() {
            pedidoEmEstadoDeCriado.getEstadoPedido().realizarPedido();

            assertAll(
                    () -> assertTrue(pedidoEmEstadoDeCriado.getEstadoPedido() instanceof RealizadoImpl),
                    () -> assertEquals(Status.REALIZADO, pedidoEmEstadoDeCriado.getStatus())
            );
        }

        @Test
        void deve_cancelarPedido_mudandoStatusParaCancelado_setandoEstadoParaCancelado() {
            pedidoEmEstadoDeCriado.getEstadoPedido().cancelarPedido();

            assertAll(
                    () -> assertTrue(pedidoEmEstadoDeCriado.getEstadoPedido() instanceof CanceladoImpl),
                    () -> assertEquals(Status.CANCELADO, pedidoEmEstadoDeCriado.getStatus())
            );
        }
    }

    @Nested
    class TestesExcecaoDeAlteracaoImpossivelDeEstado{

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarPagarPedido_EmEstadoDeCriado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeCriado.getEstadoPedido().pagarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarConfirmarPedido_EmEstadoDeCriado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeCriado.getEstadoPedido().confirmarPedido()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarDeixarPedidoPronto_EmEstadoDeCriado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeCriado.getEstadoPedido().pedidoPronto()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarSetarPedidoSaiuParaEntregar_EmEstadoDeCriado(){
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeCriado.getEstadoPedido().pedidoSaiuParaEntrega()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarSetarPedidoComoEntregue_EmEstadoDeCriado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeCriado.getEstadoPedido().pedidoEntregue()
            );
        }

        @Test
        void deve_lancarExcecaoDeStatusAo_TentarNaoAutorizarPedido_EmEstadoDeCriado() {
            assertThrows(
                    StatusInvalidoException.class,
                    () -> pedidoEmEstadoDeCriado.getEstadoPedido().naoAutorizarPedido()
            );
        }
    }
}