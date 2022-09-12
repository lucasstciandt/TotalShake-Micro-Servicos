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
import totalshake.ciandt.com.dataservicepedido.application.controller.request.put.AtualizacaoCompletaPedidoDTORequest;
import totalshake.ciandt.com.dataservicepedido.application.controller.request.put.AtualizacaoStatusPedidoDTORequest;
import totalshake.ciandt.com.dataservicepedido.application.controller.request.post.ItemPedidoDTOPostRequest;
import totalshake.ciandt.com.dataservicepedido.application.controller.request.post.PedidoDTOPostRequest;
import totalshake.ciandt.com.dataservicepedido.application.error.ApiErroCodInternoMensagem;
import totalshake.ciandt.com.dataservicepedido.builders.PedidoBuilder;
import totalshake.ciandt.com.dataservicepedido.domain.model.Pedido;
import totalshake.ciandt.com.dataservicepedido.domain.model.Status;
import totalshake.ciandt.com.dataservicepedido.domain.repository.PedidoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
            var pedidoRequest = umPedidoDtoRequestValido();

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

        @Test
        @Transactional
        public void deve_lancarExcecaoDeArgumentosInvalidos_e_devolverErrosParaOCliente() throws Exception{
            var pedidoRequestInvalido = umPedidoDtoRequestInvalido();

            String pedidoRequestJson = objectMapper.writeValueAsString(pedidoRequestInvalido);

            mockMvc.perform(post(PEDIDO_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(pedidoRequestJson)
                    )
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSP001.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSP001.getCodigo()))
                    .andExpect(jsonPath("erros").isNotEmpty());

            int registrosDePedidoNoDatabase = pedidoRepository.findAll().size();
            assertEquals(0, registrosDePedidoNoDatabase);
        }

        private PedidoDTOPostRequest umPedidoDtoRequestValido() {
            return new PedidoDTOPostRequest(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    new BigDecimal("99.99"),
                    new ArrayList<ItemPedidoDTOPostRequest>(List.of(new ItemPedidoDTOPostRequest("Arroz", 2)))
            );
        }

        private PedidoDTOPostRequest umPedidoDtoRequestInvalido() {
            return new PedidoDTOPostRequest(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    new BigDecimal("0.00"),
                    new ArrayList<ItemPedidoDTOPostRequest>()
            );
        }
    }

    @Nested
    class TestesBuscaDePedido{
        @Test
        @Transactional
        public void deve_buscarUmPedidoComSucesso_e_devolverDTOCompleto() throws Exception{
            var pedido = PedidoBuilder.umPedido().comUmItemPedido().build();

            var pedidoSalvo = pedidoRepository.save(pedido);

            mockMvc.perform(get(PEDIDO_URI +"/"+pedidoSalvo.getUuidPedido()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("uuid_pedido").isNotEmpty())
                    .andExpect(jsonPath("uuid_cliente").isNotEmpty())
                    .andExpect(jsonPath("uuid_restaurante").isNotEmpty())
                    .andExpect(jsonPath("status").isNotEmpty())
                    .andExpect(jsonPath("total").isNotEmpty())
                    .andExpect(jsonPath("ultimaAtualizacao").isNotEmpty())
                    .andExpect(jsonPath("itens").isNotEmpty())
                    .andExpect(jsonPath("dataHoraStatus").isNotEmpty());

            Pedido pedidoCriado = pedidoRepository.findAll().get(0);

            assertAll(
                    () -> assertNull(pedidoCriado.getUuidEntregador()),
                    () -> assertEquals(1, pedidoCriado.getItens().size()),
                    () -> assertNotNull(pedidoCriado.getDataHoraStatus().getDataHoraCriado()),
                    () -> assertNotNull(pedidoCriado.getTotal())
            );
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDePedidoInexistente_e_devolverDTODeErroParaOCliente() throws Exception{

            mockMvc.perform(get(PEDIDO_URI +"/"+ UUID.randomUUID()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSP002.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSP002.getCodigo()));

            int registrosDePedidoNoDatabase = pedidoRepository.findAll().size();
            assertEquals(0, registrosDePedidoNoDatabase);
        }

    }

    @Nested
    class TestesAtualizacaoStatusPedido {

        @Test
        @Transactional
        public void deve_atualizarUmPedido_e_devolverDtoComStatusHoraStatusAtualUUID() throws Exception {
            var pedido = PedidoBuilder.umPedido().comUmItemPedido().build();
            var pedidoSalvo = pedidoRepository.save(pedido);

            var atualizacaoPedidoDtoValido = umDtoAtualizacaoPedido();
            String pedidoRequestJson = objectMapper.writeValueAsString(atualizacaoPedidoDtoValido);

            mockMvc.perform(put(PEDIDO_URI + "/" +  pedidoSalvo.getUuidPedido() )
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(pedidoRequestJson)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("uuidPedido").isNotEmpty())
                    .andExpect(jsonPath("status").value(Status.REALIZADO.name()))
                    .andExpect(jsonPath("ultimaAtualizacao").isNotEmpty())
                    .andExpect(jsonPath("dataHoraStatus").isNotEmpty());

            var pedidoCriado = pedidoRepository.findAll().get(0);

            assertAll(
                    () -> assertEquals(1, pedidoCriado.getItens().size()),
                    () -> assertEquals(Status.REALIZADO, pedidoCriado.getStatus()),
                    () -> assertNotNull(pedidoCriado.getDataHoraStatus().getDataHoraCriado()),
                    () -> assertNotNull(pedidoCriado.getDataHoraStatus().getDataHoraRealizado())
            );
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDeCampoInvalidoQuando_atualizacaoNaoTiverStatus() throws Exception {
            var pedido = PedidoBuilder.umPedido().comUmItemPedido().build();
            var pedidoSalvo = pedidoRepository.save(pedido);

            var atualizacaoPedidoDtoValido = umDtoAtualizacaoInvalido();
            String pedidoRequestJson = objectMapper.writeValueAsString(atualizacaoPedidoDtoValido);

            mockMvc.perform(put(PEDIDO_URI + "/" +  pedidoSalvo.getUuidPedido() )
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(pedidoRequestJson)
                    )
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSP001.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSP001.getCodigo()))
                    .andExpect(jsonPath("erros").isNotEmpty());


            var pedidoCriado = pedidoRepository.findAll().get(0);

            assertAll(
                    () -> assertEquals(1, pedidoCriado.getItens().size()),
                    () -> assertNotEquals(Status.REALIZADO, pedidoCriado.getStatus()),
                    () -> assertNotNull(pedidoCriado.getDataHoraStatus().getDataHoraCriado()),
                    () -> assertNull(pedidoCriado.getDataHoraStatus().getDataHoraRealizado())
            );
        }

        private AtualizacaoStatusPedidoDTORequest umDtoAtualizacaoInvalido() {
            return new AtualizacaoStatusPedidoDTORequest(
                    null,
                    LocalDateTime.now()
            );
        }

        private AtualizacaoStatusPedidoDTORequest umDtoAtualizacaoPedido() {
            return new AtualizacaoStatusPedidoDTORequest(
                    Status.REALIZADO,
                    LocalDateTime.now()
            );
        }
    }

    @Nested
    class TestesAtualizacaoCompletaPedido{

        @Test
        @Transactional
        public void deve_atualizarUmPedido_e_devolverDtoCompleto() throws Exception {
            var pedido = PedidoBuilder.umPedido().comUmItemPedido().build();
            var pedidoSalvo = pedidoRepository.save(pedido);

            var atualizacaoPedidoDtoValido = umDtoAtualizacaoCompletoPedido(pedidoSalvo);

            String pedidoRequestJson = objectMapper.writeValueAsString(atualizacaoPedidoDtoValido);

            mockMvc.perform(put(PEDIDO_URI + "/atualizar" )
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(pedidoRequestJson)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("uuidPedido").isNotEmpty())
                    .andExpect(jsonPath("uuidCliente").isNotEmpty())
                    .andExpect(jsonPath("uuidRestaurante").isNotEmpty())
                    .andExpect(jsonPath("status").value(Status.REALIZADO.name()))
                    .andExpect(jsonPath("total").value(99.99))
                    .andExpect(jsonPath("ultimaAtualizacao").isNotEmpty())
                    .andExpect(jsonPath("dataHoraStatus").isNotEmpty());

            var pedidoCriado = pedidoRepository.findAll().get(0);

            assertAll(
                    () -> assertEquals(1, pedidoCriado.getItens().size()),
                    () -> assertEquals(Status.REALIZADO, pedidoCriado.getStatus()),
                    () -> assertEquals(new BigDecimal("99.99"),pedidoCriado.getTotal()),
                    () -> assertNotNull(pedidoCriado.getDataHoraStatus())
            );
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDeCampoInvalidoQuando_atualizacaoNaoTiverCamposObrigatorios() throws Exception {
            var pedido = PedidoBuilder.umPedido().comUmItemPedido().build();
            pedidoRepository.save(pedido);

            var atualizacaoPedidoDtoInValido = umDtoAtualizacaoCompletoPedidoInvalido();
            String pedidoRequestJson = objectMapper.writeValueAsString(atualizacaoPedidoDtoInValido);

            mockMvc.perform(put(PEDIDO_URI + "/atualizar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(pedidoRequestJson)
                    )
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSP001.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSP001.getCodigo()))
                    .andExpect(jsonPath("erros").isNotEmpty());


            var pedidoCriado = pedidoRepository.findAll().get(0);

            assertAll(
                    () -> assertEquals(1, pedidoCriado.getItens().size()),
                    () -> assertNotEquals(Status.REALIZADO, pedidoCriado.getStatus()),
                    () -> assertNotEquals(new BigDecimal("99.99"), pedidoCriado.getTotal()),
                    () -> assertNull(pedidoCriado.getDataHoraStatus().getDataHoraRealizado())
            );
        }

        private AtualizacaoCompletaPedidoDTORequest umDtoAtualizacaoCompletoPedidoInvalido() {
            return new AtualizacaoCompletaPedidoDTORequest(
                    "xcasdasdasd-dsdasda",
                    null,
                    null,
                    null,
                    Status.REALIZADO,
                    new BigDecimal("99.99"),
                    null,
                    null
            );
        }

        private AtualizacaoCompletaPedidoDTORequest umDtoAtualizacaoCompletoPedido(Pedido pedido) {
            return new AtualizacaoCompletaPedidoDTORequest(
                    pedido.getUuidPedido().toString(),
                    pedido.getUuidCliente().toString(),
                    pedido.getUuidRestaurante().toString(),
                    null,
                    Status.REALIZADO,
                    new BigDecimal("99.99"),
                    pedido.getItens(),
                    pedido.getDataHoraStatus()
            );
        }
    }
}