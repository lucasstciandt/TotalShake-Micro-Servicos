package totalshake.ciandt.com.apipagamento.domain.service;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.apipagamento.proxy.DataServiceClienteProxy;
import totalshake.ciandt.com.apipagamento.proxy.DataServicePedidoProxy;

@Service
public class PagamentoService {

    private final DataServiceClienteProxy dataServiceCliente;
    private final DataServicePedidoProxy dataServicePedido;

    public PagamentoService(DataServiceClienteProxy dataServiceCliente, DataServicePedidoProxy dataServicePedido) {
        this.dataServiceCliente = dataServiceCliente;
        this.dataServicePedido = dataServicePedido;
    }
}
