package totalshake.ciandt.com.dataserviceclient.domain.service;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.dataserviceclient.application.controller.request.ClienteDTOPostRequest;
import totalshake.ciandt.com.dataserviceclient.application.controller.response.ClienteDTOResponse;
import totalshake.ciandt.com.dataserviceclient.domain.repository.ClienteRepository;

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
}
