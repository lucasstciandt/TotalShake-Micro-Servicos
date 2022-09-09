package totalshake.ciandt.com.dataservicepedido.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import totalshake.ciandt.com.dataservicepedido.controller.request.PedidoDTOPostRequest;
import totalshake.ciandt.com.dataservicepedido.controller.response.PedidoDTOResponse;
import totalshake.ciandt.com.dataservicepedido.domain.service.PedidoCrudService;

import javax.validation.Valid;

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
}
