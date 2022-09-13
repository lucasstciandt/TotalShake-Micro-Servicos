package totalshake.ciandt.com.dataservicecliente.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totalshake.ciandt.com.dataservicecliente.application.controller.request.ClienteDTOPostRequest;
import totalshake.ciandt.com.dataservicecliente.application.controller.request.SaldoParaAdicionarDTORequest;
import totalshake.ciandt.com.dataservicecliente.application.controller.response.ClienteDTOResponse;
import totalshake.ciandt.com.dataservicecliente.domain.service.ClienteCrudService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    private final ClienteCrudService clienteCrudService;

    public ClienteController(ClienteCrudService clienteCrudService) {
        this.clienteCrudService = clienteCrudService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTOResponse> criarCliente(@RequestBody @Valid ClienteDTOPostRequest clientPost){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCrudService.salvarCliente(clientPost));
    }

    @GetMapping("/{uuidCliente}")
    public ResponseEntity<ClienteDTOResponse> buscarCliente(@PathVariable UUID uuidCliente){
        return ResponseEntity.ok(clienteCrudService.buscarClienteDTO(uuidCliente));
    }

    @PutMapping("/{uuidCliente}/adicionar-saldo/{valor}")
    public ResponseEntity<ClienteDTOResponse> adicionarSaldo(@PathVariable UUID uuidCliente,
                                                            @PathVariable BigDecimal valor){
        return ResponseEntity.ok(clienteCrudService.adicionarSaldo(uuidCliente, valor));
    }

    @GetMapping("/{uuidCliente}/saldo-carteira")
    public ResponseEntity<ClienteDTOResponse> buscarSaldoCarteira(@PathVariable UUID uuidCliente){
        return ResponseEntity.ok(clienteCrudService.buscarSaldoCarteira(uuidCliente));
    }
}
