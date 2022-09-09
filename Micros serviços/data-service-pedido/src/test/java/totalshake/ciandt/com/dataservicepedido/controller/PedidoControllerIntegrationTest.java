package totalshake.ciandt.com.dataservicepedido.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import totalshake.ciandt.com.dataservicepedido.controller.request.ItemPedidoDTO;
import totalshake.ciandt.com.dataservicepedido.controller.request.PedidoDTOPostRequest;
import totalshake.ciandt.com.dataservicepedido.domain.model.Status;
import totalshake.ciandt.com.dataservicepedido.domain.repository.PedidoRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
class PedidoControllerIntegrationTest {

    private final String PEDIDO_URI = "/v1/pedidos";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void cleanDatabase(){
        pedidoRepository.deleteAll();
    }

    @AfterEach
    public void tearDown(){
        pedidoRepository.deleteAll();
    }

    @Nested
    class TestesCriacaoPedido{
        @Test
        @Transactional
        public void deve_criarUmPedidoComUmItem_e_devolverDTOPedidoStatusCriado() throws Exception{
            var pedidoRequest = umPedidoDtoRequesValido();

            String pedidoRequestJson = objectMapper.writeValueAsString(pedidoRequest);

            mockMvc.perform(post(PEDIDO_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(pedidoRequestJson)
                    )
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("uuid_pedido").isNotEmpty());

            var pedidoCriado = pedidoRepository.findAll().get(0);

            assertAll(
                    () -> assertEquals(1, pedidoCriado.getItens().size()),
                    () -> assertEquals(Status.CRIADO, pedidoCriado.getStatus()),
                    () -> assertNotNull(pedidoCriado.getDataHoraStatus().getDataHoraCriado())
            );
        }

        /*
        @Test
        @Transactional
        public void deve_lancarExcecaoDeArgumentosInvalidos_e_devolverErrosParaOCliente() throws Exception{
            var pedidoRequestInvalido = umPedidoComItensInvalidos();

            String pedidoRequestJson = objectMapper.writeValueAsString(pedidoRequestInvalido);

            mockMvc.perform(post(PEDIDO_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(pedidoRequestJson)
                    )
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("mensagem").value(CodInternoErroApi.AP001.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(CodInternoErroApi.AP001.getCodigo()))
                    .andExpect(jsonPath("erros").isNotEmpty());

            int registrosDePedidoNoDatabase = pedidoRepository.findAll().size();
            assertEquals(0, registrosDePedidoNoDatabase);
        }*/

        private PedidoDTOPostRequest umPedidoDtoRequesValido() {
            var pedidoDtoValido = new PedidoDTOPostRequest(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    new BigDecimal("99.99"),
                    new ArrayList<ItemPedidoDTO>(List.of(new ItemPedidoDTO("Arroz", 2)))
            );

            return pedidoDtoValido;
        }

        /*
        private PedidoDTOPostRequest umPedidoRequestValido() {
            var pedidoDto = new PedidoDTOPostRequest(

            );
            return pedidoDto;
        }*/
    }

}