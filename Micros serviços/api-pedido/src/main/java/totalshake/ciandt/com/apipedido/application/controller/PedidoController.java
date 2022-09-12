package totalshake.ciandt.com.apipedido.application.controller;

import org.springframework.http.HttpStatus;
import totalshake.ciandt.com.apipedido.application.controller.request.ItemPedidoDTO;
import totalshake.ciandt.com.apipedido.proxy.put.response.PedidoDTOGetResponse;
import totalshake.ciandt.com.apipedido.domain.service.crud.PedidoCrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoCrudService pedidoCrudService;

    public PedidoController(PedidoCrudService pedidoCrudService) {
        this.pedidoCrudService = pedidoCrudService;
    }

    @PutMapping("/{uuidPedido}/itens/{itemId}/acrescentar/{quantidade}")
    public ResponseEntity<PedidoDTOGetResponse> acrescentarItem(@PathVariable UUID uuidPedido, @PathVariable Long itemId,
                                                                @PathVariable Integer quantidade){
        return ResponseEntity.ok(pedidoCrudService.acrescentarItem(uuidPedido, itemId, quantidade));
    }

    @PutMapping("/{uuidPedido}/itens/{itemId}/reduzir/{quantidade}")
    public ResponseEntity<PedidoDTOGetResponse> reduzirItem(@PathVariable UUID uuidPedido, @PathVariable Long itemId,
                                                         @PathVariable Integer quantidade){
        return ResponseEntity.ok(pedidoCrudService.reduzirQuantidadeItem(uuidPedido, itemId, quantidade));
    }

    @PostMapping("/{uuidPedido}/adicionar-item")
    public ResponseEntity<PedidoDTOGetResponse> adicionarItem(@PathVariable UUID uuidPedido,
                                                           @RequestBody @Valid ItemPedidoDTO itemPedidoDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoCrudService.adicionarItemNoPedido(uuidPedido, itemPedidoDTO));
    }

    @PutMapping("/{uuidPedido}/realizar")
    public ResponseEntity<PedidoDTOGetResponse> realizarPedido(@PathVariable UUID uuidPedido){
        return ResponseEntity.ok(pedidoCrudService.realizarPedido(uuidPedido));
    }

    @PutMapping("/{uuidPedido}/cancelar")
    public ResponseEntity<PedidoDTOGetResponse> cancelarPedido(@PathVariable UUID uuidPedido){
        return ResponseEntity.ok(pedidoCrudService.cancelarPedido(uuidPedido));
    }
}
