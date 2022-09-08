package totalshake.ciandt.com.dataservicecliente.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totalshake.ciandt.com.dataservicecliente.application.controller.request.ClienteDTOPostRequest;
import totalshake.ciandt.com.dataservicecliente.application.controller.response.ClienteDTOResponse;
import totalshake.ciandt.com.dataservicecliente.domain.service.ClienteCrudService;

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
        return ResponseEntity.ok(clienteCrudService.buscarClienteDTO(uuidCliente));
    }
}
