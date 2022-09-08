package totalshake.ciandt.com.dataserviceentregador.application.controller;

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
import totalshake.ciandt.com.dataserviceentregador.application.controller.request.EntregadorDTOPostRequest;
import totalshake.ciandt.com.dataserviceentregador.application.error.ApiErroCodInternoMensagem;
import totalshake.ciandt.com.dataserviceentregador.builder.EntregadorBuilder;
import totalshake.ciandt.com.dataserviceentregador.domain.repository.EntregadorRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EntregadorIntegrationControllerTest {

    private final String ENTREGADOR_URI = "/v1/entregadores";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void cleanDatabase(){
        entregadorRepository.deleteAll();
    }

    @AfterEach
    public void tearDown(){
        entregadorRepository.deleteAll();
    }


    @Nested
    class TestesCriacaoRestaurante{

        @Test
        @Transactional
        public void deve_criarEntregador_e_devolverDTOComNomeEUUID() throws Exception{
            var entregadorRequest = umEntregadorDtoValido();

            String entregadorJsonRequest = objectMapper.writeValueAsString(entregadorRequest);

            mockMvc.perform(post(ENTREGADOR_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(entregadorJsonRequest)
                    )
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("uuid").isNotEmpty())
                    .andExpect(jsonPath("nome").isNotEmpty());

            var restauranteCriado = entregadorRepository.findAll().get(0);

            assertNotNull(restauranteCriado);
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDeArgumentosInvalidos_e_devolverErrosParaOCliente() throws Exception{
            var restauranteDtoRequest = umEntregadorDtoInvalido();

            String restauranteJsonRequest = objectMapper.writeValueAsString(restauranteDtoRequest);

            mockMvc.perform(post(ENTREGADOR_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(restauranteJsonRequest)
                    )
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSE001.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSE001.getCodigo()))
                    .andExpect(jsonPath("erros").isNotEmpty());

            int quantidadeRegistroEntregadoresDatabase = entregadorRepository.findAll().size();
            assertEquals(0, quantidadeRegistroEntregadoresDatabase);
        }

        private EntregadorDTOPostRequest umEntregadorDtoInvalido() {
            return new EntregadorDTOPostRequest(
                    " ",
                    "1234567"
            );
        }

        private EntregadorDTOPostRequest umEntregadorDtoValido() {

            return new EntregadorDTOPostRequest(
                    "Moises Ribeiro",
                    "12345678911"
            );
        }

    }

    @Nested
    class TestesBuscarRestaurante{

        @Test
        @Transactional
        public void deve_buscarUmEntregadorComSucesso_e_devolverDTOCompleto() throws Exception{
            var entregador = EntregadorBuilder.umEntregador().build();

            var entregadorSalvo = entregadorRepository.save(entregador);

            mockMvc.perform(get(ENTREGADOR_URI +"/"+entregadorSalvo.getUuidEntregador()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("uuid").isNotEmpty())
                    .andExpect(jsonPath("nome").isNotEmpty())
                    .andExpect(jsonPath("cpf").isNotEmpty());

            var clienteCriado = entregadorRepository.findAll().get(0);
            assertEquals(clienteCriado, entregadorSalvo);
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDeRestauranteInexistente_e_devolverErroParaOCliente() throws Exception{

            mockMvc.perform(get(ENTREGADOR_URI +"/"+ UUID.randomUUID()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSE002.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSE002.getCodigo()));

            int quantidadeRegistrosEntregadoresDatabase = entregadorRepository.findAll().size();
            assertEquals(0, quantidadeRegistrosEntregadoresDatabase);
        }
    }

}