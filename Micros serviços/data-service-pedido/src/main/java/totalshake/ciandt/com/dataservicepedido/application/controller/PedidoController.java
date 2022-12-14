package totalshake.ciandt.com.dataservicepedido.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totalshake.ciandt.com.dataservicepedido.application.controller.request.put.AtualizacaoCompletaPedidoDTORequest;
import totalshake.ciandt.com.dataservicepedido.application.controller.request.post.PedidoDTOPostRequest;
import totalshake.ciandt.com.dataservicepedido.application.controller.response.PedidoDTOResponse;
import totalshake.ciandt.com.dataservicepedido.domain.service.PedidoCrudService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    private final PedidoCrudService pedidoCrudService;

    public PedidoController(PedidoCrudService pedidoCrudService) {
        this.pedidoCrudService = pedidoCrudService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTOResponse> criarPedido(@RequestBody @Valid PedidoDTOPostRequest pedidoPostRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoCrudService.criarPedido(pedidoPostRequest));
    }

    @GetMapping("/{uuidPedido}")
    public ResponseEntity<PedidoDTOResponse> buscarPedido(@PathVariable UUID uuidPedido){
        return ResponseEntity.ok(pedidoCrudService.buscarPedidoDto(uuidPedido));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<PedidoDTOResponse> atualizarPedido(
            @RequestBody @Valid AtualizacaoCompletaPedidoDTORequest pedidoAtualizado
    ){
        return ResponseEntity.ok(pedidoCrudService.atualizarPedido(pedidoAtualizado));
    }

    @GetMapping("/{uuidPedido}/total")
    public ResponseEntity<PedidoDTOResponse> totalPedido(@PathVariable UUID uuidPedido){
        return ResponseEntity.ok(pedidoCrudService.buscarTotalPedido(uuidPedido));
    }
}
