package totalshake.ciandt.com.apipagamento.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "data-service-pedido")
public interface DataServicePedidoProxy {

    @GetMapping("/v1/pedidos/{uuidPedido}/total")
    Map<String, BigDecimal> buscarTotalPedido(@PathVariable UUID uuidPedido);
}
