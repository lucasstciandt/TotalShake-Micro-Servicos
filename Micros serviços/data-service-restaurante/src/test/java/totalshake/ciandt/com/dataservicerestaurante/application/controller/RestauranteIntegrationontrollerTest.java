package totalshake.ciandt.com.dataservicerestaurante.application.controller;

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
import totalshake.ciandt.com.dataservicerestaurante.application.controller.request.RestauranteDTOPostRequest;
import totalshake.ciandt.com.dataservicerestaurante.application.error.ApiErroCodInternoMensagem;
import totalshake.ciandt.com.dataservicerestaurante.builder.RestauranteBuilder;
import totalshake.ciandt.com.dataservicerestaurante.domain.repository.RestauranteRepository;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RestauranteIntegrationControllerTest {

    private final String RESTAURANTE_URI = "/v1/restaurantes";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void cleanDatabase(){
        restauranteRepository.deleteAll();
    }

    @AfterEach
    public void tearDown(){
        restauranteRepository.deleteAll();
    }


    @Nested
    class TestesCriacaoRestaurante{

        @Test
        @Transactional
        public void deve_criarUmRestaurante_e_devolverDTOComNomeEUUID() throws Exception{
            var restauranteRequest = umRestauranteDTOValido();

            String restauranteJsonRequest = objectMapper.writeValueAsString(restauranteRequest);

            mockMvc.perform(post(RESTAURANTE_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(restauranteJsonRequest)
                    )
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("uuid").isNotEmpty())
                    .andExpect(jsonPath("nome").isNotEmpty());

            var restauranteCriado = restauranteRepository.findAll().get(0);

            assertNotNull(restauranteCriado);
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDeArgumentosInvalidos_e_devolverErrosParaOCliente() throws Exception{
            var restauranteDtoRequest = umClienteDtoInvalido();

            String restauranteJsonRequest = objectMapper.writeValueAsString(restauranteDtoRequest);

            mockMvc.perform(post(RESTAURANTE_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(restauranteJsonRequest)
                    )
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSR001.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSR001.getCodigo()))
                    .andExpect(jsonPath("erros").isNotEmpty());

            int quantidadeRegistrosRestauranteDatabase = restauranteRepository.findAll().size();
            assertEquals(0, quantidadeRegistrosRestauranteDatabase);
        }

        private RestauranteDTOPostRequest umClienteDtoInvalido() {
            return new RestauranteDTOPostRequest(
                    " ",
                    "1234567"
            );
        }

        private RestauranteDTOPostRequest umRestauranteDTOValido() {

            return new RestauranteDTOPostRequest(
                    "Total Shake lapa",
                    "12345678911234"
            );
        }

    }

    @Nested
    class TestesBuscarRestaurante{

        @Test
        @Transactional
        public void deve_buscarUmRestauranteComSucesso_e_devolverDTOCompleto() throws Exception{
            var restaurante = RestauranteBuilder.umRestaurante().build();

            var restauranteSalvo = restauranteRepository.save(restaurante);

            mockMvc.perform(get(RESTAURANTE_URI +"/"+restauranteSalvo.getUuidRestaurante()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("uuid").isNotEmpty())
                    .andExpect(jsonPath("nome").isNotEmpty())
                    .andExpect(jsonPath("cnpj").isNotEmpty());

            var clienteCriado = restauranteRepository.findAll().get(0);
            assertEquals(clienteCriado, restauranteSalvo);
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDeRestauranteInexistente_e_devolverErroParaOCliente() throws Exception{

            mockMvc.perform(get(RESTAURANTE_URI +"/"+ UUID.randomUUID()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSR002.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSR002.getCodigo()));

            int quantidadeRegistrosRestauranteDatabase = restauranteRepository.findAll().size();
            assertEquals(0, quantidadeRegistrosRestauranteDatabase);
        }
    }

}