package totalshake.ciandt.com.apipedido.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import totalshake.ciandt.com.apipedido.proxy.response.PedidoDTOGetResponse;

import java.util.UUID;

@FeignClient(name = "data-service-pedido")
public interface DataServicePedidoProxy {

    @GetMapping("/v1/pedidos/{uuidPedido}")
    PedidoDTOGetResponse buscarPedido(@PathVariable UUID uuidPedido);

}
