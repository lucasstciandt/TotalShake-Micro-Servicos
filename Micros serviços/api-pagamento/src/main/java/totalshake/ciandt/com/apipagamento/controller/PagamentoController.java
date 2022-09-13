package totalshake.ciandt.com.apipagamento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import totalshake.ciandt.com.apipagamento.domain.service.PagamentoService;

@RestController
@RequestMapping("/v1/api-pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }


}
