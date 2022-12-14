package totalshake.ciandt.com.dataservicecliente.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import totalshake.ciandt.com.dataservicecliente.application.controller.request.ClienteDTOPostRequest;
import totalshake.ciandt.com.dataservicecliente.application.error.ApiErroCodInternoMensagem;
import totalshake.ciandt.com.dataservicecliente.builder.ClienteBuilder;
import totalshake.ciandt.com.dataservicecliente.domain.model.Cliente;
import totalshake.ciandt.com.dataservicecliente.domain.repository.ClienteRepository;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClienteIntegrationTests {

    private final String CLIENTE_URI = "/v1/clientes";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void cleanDatabase(){
        clienteRepository.deleteAll();
    }

    @AfterEach
    public void tearDown(){
        clienteRepository.deleteAll();
    }

    @Nested
    class TestesCriacaoCliente{

        @Test
        @Transactional
        public void deve_criarUmCliente_e_devolverDTOComNomeESaldoEUUID() throws Exception{
            var clienteRequest = umClienteDtoValido();

            String clientePostRequest = objectMapper.writeValueAsString(clienteRequest);

            mockMvc.perform(post(CLIENTE_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(clientePostRequest)
                    )
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("uuid").isNotEmpty())
                    .andExpect(jsonPath("nome").isNotEmpty())
                    .andExpect(jsonPath("saldo").value(0.00));

            var clienteCriado = clienteRepository.findAll().get(0);

            assertAll(
                    () -> assertNotNull(clienteCriado),
                    () -> assertNotNull(clienteCriado.getEndereco()),
                    () -> assertEquals(clienteCriado.getSaldo(), new BigDecimal("0.00"))
            );
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDeArgumentosInvalidos_e_devolverErrosParaOCliente() throws Exception{
            var clienteRequest = umClienteDtoInvalido();

            String pedidoRequestJson = objectMapper.writeValueAsString(clienteRequest);

            mockMvc.perform(post(CLIENTE_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(pedidoRequestJson)
                    )
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSC001.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSC001.getCodigo()))
                    .andExpect(jsonPath("erros").isNotEmpty());

            int quantidadeRegistrosClienteDatabase = clienteRepository.findAll().size();
            assertEquals(0, quantidadeRegistrosClienteDatabase);
        }

        private ClienteDTOPostRequest umClienteDtoInvalido() {
            return new ClienteDTOPostRequest(
                    " ",
                    "1234567",
                    "M",
                    0,
                    "08"
            );
        }

        private ClienteDTOPostRequest umClienteDtoValido() {

            return new ClienteDTOPostRequest(
                    "Lauro Roberto Miguel",
                    "12345678911",
                    "Mamoneira",
                    33,
                    "08080808"
            );
        }
    }

    @Nested
    class TestesBuscaCliente{
        @Test
        @Transactional
        public void deve_buscarUmClienteComSucesso_e_devolverDTOCompleto() throws Exception{
            var cliente = ClienteBuilder.umCliente().comEndereco().build();

            var clienteSalvo = clienteRepository.save(cliente);

            mockMvc.perform(get(CLIENTE_URI +"/"+clienteSalvo.getUuidCliente()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("uuid").isNotEmpty())
                    .andExpect(jsonPath("nome").isNotEmpty())
                    .andExpect(jsonPath("cpf").isNotEmpty())
                    .andExpect(jsonPath("saldo").isNotEmpty())
                    .andExpect(jsonPath("rua").isNotEmpty())
                    .andExpect(jsonPath("numeroCasa").isNotEmpty())
                    .andExpect(jsonPath("cep").isNotEmpty());

            Cliente clienteCriado = clienteRepository.findAll().get(0);
            assertEquals(clienteCriado, clienteSalvo);
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDeClienteInexistente_e_devolverErroParaOCliente() throws Exception{

            mockMvc.perform(get(CLIENTE_URI +"/"+ UUID.randomUUID()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSC002.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSC002.getCodigo()));

            int quantidadeRegistrosClienteDatabase = clienteRepository.findAll().size();
            assertEquals(0, quantidadeRegistrosClienteDatabase);
        }
    }

    @Nested
    class TestesBuscarSaldoCliente{
        @Test
        @Transactional
        public void deve_buscarSaldoCarteiraCliente_e_devolverDTOComSaldo() throws Exception{
            var cliente = ClienteBuilder.umCliente().comEndereco().build();
            cliente.setSaldo(new BigDecimal("100.48"));

            var clienteSalvo = clienteRepository.save(cliente);

            mockMvc.perform(get(CLIENTE_URI + "/" +clienteSalvo.getUuidCliente() +"/saldo-carteira"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("saldo").isNotEmpty())
                    .andExpect(jsonPath("saldo").value(new BigDecimal("100.48")));

            Cliente clienteBuscado = clienteRepository.findAll().get(0);
            assertEquals(new BigDecimal("100.48"), clienteBuscado.getSaldo());
        }

        @Test
        @Transactional
        public void deve_lancarExcecaoDeClienteInexistente_e_devolverErroParaOCliente() throws Exception{

            mockMvc.perform(get(CLIENTE_URI +"/"+ UUID.randomUUID()+"/saldo-carteira"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSC002.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSC002.getCodigo()));

            int quantidadeRegistrosClienteDatabase = clienteRepository.findAll().size();
            assertEquals(0, quantidadeRegistrosClienteDatabase);
        }
    }

    @Nested
    class TestesAdicionarSaldoCliente{

        @Test
        @Transactional
        public void deve_adicionarSaldoAoCliente_e_devolverDtoComUuidNomeSaldo() throws Exception{
            var cliente = ClienteBuilder.umCliente().comEndereco().build();

            var clienteSalvo = clienteRepository.save(cliente);

            var saldoParaAdicao = new BigDecimal("70.99");

            mockMvc.perform(put(
                    CLIENTE_URI +"/" +clienteSalvo.getUuidCliente()
                            +"/adicionar-saldo/"+ saldoParaAdicao)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("uuid").isNotEmpty())
                    .andExpect(jsonPath("nome").isNotEmpty())
                    .andExpect(jsonPath("saldo").isNotEmpty())
                    .andExpect(jsonPath("saldo").value(new BigDecimal("70.99")));

            Cliente clienteExistente = clienteRepository.findAll().get(0);
            assertEquals(clienteExistente.getSaldo(), new BigDecimal("70.99"));
        }


        @Test
        @Transactional
        public void deve_lancarExcecaoDeValorInvalidoAoTentarAdicionarSaldo_e_devolverDTOErro() throws Exception{
            var cliente = ClienteBuilder.umCliente().comEndereco().build();
            cliente.setSaldo(new BigDecimal("70.99"));

            var clienteSalvo = clienteRepository.save(cliente);

            var saldoParaAdicao = new BigDecimal("0.00");

            mockMvc.perform(put(
                            CLIENTE_URI +"/" + clienteSalvo.getUuidCliente()
                                    +"/adicionar-saldo/"+ saldoParaAdicao)
                    )
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("httpCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("mensagem").value(ApiErroCodInternoMensagem.DSC101.getMensagem()))
                    .andExpect(jsonPath("codInterno").value(ApiErroCodInternoMensagem.DSC101.getCodigo()));

            Cliente clienteExistente = clienteRepository.findAll().get(0);
            assertEquals(clienteExistente.getSaldo(), new BigDecimal("70.99"));
        }
    }
}