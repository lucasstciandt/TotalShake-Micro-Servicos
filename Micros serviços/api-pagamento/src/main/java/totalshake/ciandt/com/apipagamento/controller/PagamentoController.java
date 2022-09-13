package totalshake.ciandt.com.apipagamento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import totalshake.ciandt.com.apipagamento.controller.request.PagamentoRequestDTO;
import totalshake.ciandt.com.apipagamento.controller.response.PagamentoDTOResponse;
import totalshake.ciandt.com.apipagamento.domain.service.PagamentoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api-pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoDTOResponse> efetuarPagamento(
            @RequestBody @Valid PagamentoRequestDTO pagamentoRequest
    ){
        return ResponseEntity.ok(pagamentoService.efetuarPagamento(pagamentoRequest));
    }


}
