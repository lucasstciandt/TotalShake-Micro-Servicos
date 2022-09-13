package totalshake.ciandt.com.apipagamento.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "data-service-cliente")
public interface DataServiceClienteProxy {

    @GetMapping("/v1/clientes/{uuidCliente}/saldo-carteira")
    Map<String, BigDecimal> buscarSaldoCarteiraCliente(@PathVariable UUID uuidCliente);
}
