package totalshake.ciandt.com.dataservicecliente.domain.service;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.dataservicecliente.application.controller.request.ClienteDTOPostRequest;
import totalshake.ciandt.com.dataservicecliente.application.controller.request.SaldoParaAdicionarDTORequest;
import totalshake.ciandt.com.dataservicecliente.application.controller.response.ClienteDTOResponse;
import totalshake.ciandt.com.dataservicecliente.application.error.ApiErroCodInternoMensagem;
import totalshake.ciandt.com.dataservicecliente.application.error.exceptions.ClienteInexistenteException;
import totalshake.ciandt.com.dataservicecliente.application.error.exceptions.ValorInvalidoException;
import totalshake.ciandt.com.dataservicecliente.domain.model.Cliente;
import totalshake.ciandt.com.dataservicecliente.domain.repository.ClienteRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ClienteCrudService {

    private final ClienteRepository clienteRepository;

    public ClienteCrudService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTOResponse salvarCliente(ClienteDTOPostRequest clientDtoPost){
        var cliente = clientDtoPost.toClienteModel();
        cliente = clienteRepository.save(cliente);
        return new ClienteDTOResponse(cliente.getUuidCliente(), cliente.getNome(), cliente.getSaldo());
    }

    public ClienteDTOResponse buscarClienteDTO(UUID uuidCliente){
        var cliente = this.buscarCliente(uuidCliente);

        return new ClienteDTOResponse(
                cliente.getUuidCliente(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getSaldo(),
                cliente.getEndereco().getRua(),
                cliente.getEndereco().getNumero(),
                cliente.getEndereco().getCep()
        );
    }

    public Cliente buscarCliente(UUID uuidCliente) {
       return  clienteRepository.findById(uuidCliente)
               .orElseThrow(() -> new ClienteInexistenteException(
                       ApiErroCodInternoMensagem.DSC002.getCodigo(),
                       ApiErroCodInternoMensagem.DSC002.getMensagem())
               );
    }

    public ClienteDTOResponse adicionarSaldo(UUID uuidCliente, BigDecimal valor) {
        this.verificarQuantia(valor);

        var cliente = this.buscarCliente(uuidCliente);
        BigDecimal saldoCliente = cliente.getSaldo();
        BigDecimal novoSaldo = saldoCliente.add(valor);

        cliente.setSaldo(novoSaldo);

        cliente = clienteRepository.save(cliente);

        return new ClienteDTOResponse(
                cliente.getUuidCliente(),
                cliente.getNome(),
                cliente.getSaldo()
        );
    }

    private void verificarQuantia(BigDecimal valor) {
        if(valor == null || valor.compareTo(new BigDecimal("0")) < 1){
            throw new ValorInvalidoException(
                    ApiErroCodInternoMensagem.DSC101.getCodigo(),
                    ApiErroCodInternoMensagem.DSC101.getMensagem()
            );
        }
    }
}
