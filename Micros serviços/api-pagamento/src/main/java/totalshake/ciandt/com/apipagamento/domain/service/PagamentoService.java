package totalshake.ciandt.com.apipagamento.domain.service;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.apipagamento.controller.request.PagamentoRequestDTO;
import totalshake.ciandt.com.apipagamento.controller.response.PagamentoDTOResponse;
import totalshake.ciandt.com.apipagamento.domain.model.FormaPagamento;
import totalshake.ciandt.com.apipagamento.domain.model.Pagamento;
import totalshake.ciandt.com.apipagamento.domain.model.Status;
import totalshake.ciandt.com.apipagamento.domain.repository.PagamentoRepository;
import totalshake.ciandt.com.apipagamento.proxy.DataServiceClienteProxy;
import totalshake.ciandt.com.apipagamento.proxy.DataServicePedidoProxy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final DataServiceClienteProxy dataServiceCliente;
    private final DataServicePedidoProxy dataServicePedido;
    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(DataServiceClienteProxy dataServiceCliente, DataServicePedidoProxy dataServicePedido,
                            PagamentoRepository pagamentoRepository) {
        this.dataServiceCliente = dataServiceCliente;
        this.dataServicePedido = dataServicePedido;
        this.pagamentoRepository = pagamentoRepository;
    }

    public PagamentoDTOResponse efetuarPagamento(PagamentoRequestDTO pagamentoRequest) {
        var uuidPedido = pagamentoRequest.getUuidPedido();
        var uuidCliente = pagamentoRequest.getUuidCliente();

        var saldoCliente = dataServiceCliente.buscarSaldoCarteiraCliente(uuidCliente);
        var totalPedido = dataServicePedido.buscarTotalPedido(uuidPedido);

        var statusPagamento = this.avaliarSituacaoPagamento(saldoCliente.get("saldo"), totalPedido.get("total"));

        var pagamento = new Pagamento();
        pagamento.setUuidPedido(uuidPedido);
        pagamento.setUuidCliente(uuidCliente);
        pagamento.setUuidRestaurante(pagamentoRequest.getUuidRestaurante());
        pagamento.setDataHoraPagamento(LocalDateTime.now());
        pagamento.setStatus(statusPagamento);
        pagamento.setFormaPagamento(FormaPagamento.SALDO_CARTEIRA);

        pagamento = pagamentoRepository.save(pagamento);

        return new PagamentoDTOResponse(pagamento.getStatus());
    }

    private Status avaliarSituacaoPagamento(BigDecimal saldoCliente, BigDecimal totalPedido) {
        if(saldoCliente.min(totalPedido).compareTo(new BigDecimal("0.00")) < 0){
            return Status.NAO_AUTORIZADO;
        }

        return Status.PAGO;
    }
}
