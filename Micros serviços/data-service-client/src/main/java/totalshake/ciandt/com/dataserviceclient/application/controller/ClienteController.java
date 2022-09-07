package totalshake.ciandt.com.dataserviceclient.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totalshake.ciandt.com.dataserviceclient.application.controller.request.ClienteDTOPostRequest;
import totalshake.ciandt.com.dataserviceclient.application.controller.response.ClienteDTOResponse;
import totalshake.ciandt.com.dataserviceclient.domain.service.ClienteCrudService;

import javax.validation.Valid;
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
        return null;
    }

    @PutMapping("/atualizar/{uuidCliente}")
    public ResponseEntity<ClienteDTOResponse> atualizarCliente(@PathVariable UUID uuidCliente,
                                                               @RequestBody ClienteDTOPostRequest clienteBody){
        return null;
    }
}
