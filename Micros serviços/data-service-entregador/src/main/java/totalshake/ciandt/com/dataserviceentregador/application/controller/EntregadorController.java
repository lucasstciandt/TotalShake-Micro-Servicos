package totalshake.ciandt.com.dataserviceentregador.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totalshake.ciandt.com.dataserviceentregador.application.controller.request.EntregadorDTOPostRequest;
import totalshake.ciandt.com.dataserviceentregador.application.controller.response.EntregadorDTOResponse;
import totalshake.ciandt.com.dataserviceentregador.domain.service.EntregadorCrudService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/entregadores")
public class EntregadorController {

    private final EntregadorCrudService entregadorCrudService;

    public EntregadorController(EntregadorCrudService entregadorCrudService) {
        this.entregadorCrudService = entregadorCrudService;
    }

    @PostMapping
    public ResponseEntity<EntregadorDTOResponse> criarEntregador(
            @RequestBody @Valid EntregadorDTOPostRequest entregadorRequest
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entregadorCrudService.salvarEntregador(entregadorRequest));
    }

    @GetMapping("/{uuidEntregador}")
    public ResponseEntity<EntregadorDTOResponse> buscarEntregador(@PathVariable UUID uuidEntregador){
        return ResponseEntity.ok(entregadorCrudService.buscarEntregadorDto(uuidEntregador));
    }
}
