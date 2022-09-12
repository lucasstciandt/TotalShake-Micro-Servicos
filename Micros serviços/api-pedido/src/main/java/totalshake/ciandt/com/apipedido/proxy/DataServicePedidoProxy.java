package totalshake.ciandt.com.apipedido.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import totalshake.ciandt.com.apipedido.proxy.dataservicepedido.put.AtualizacaoPedidoCompletaDTORequest;
import totalshake.ciandt.com.apipedido.proxy.dataservicepedido.put.response.PedidoDTOGetResponse;

import java.util.UUID;

@FeignClient(name = "data-service-pedido")
public interface DataServicePedidoProxy {

    @GetMapping("/v1/pedidos/{uuidPedido}")
    PedidoDTOGetResponse buscarPedido(@PathVariable UUID uuidPedido);

    @PutMapping("/v1/pedidos/atualizar")
    PedidoDTOGetResponse atualizarPedido(@RequestBody AtualizacaoPedidoCompletaDTORequest pedidoAtualizado);
}
