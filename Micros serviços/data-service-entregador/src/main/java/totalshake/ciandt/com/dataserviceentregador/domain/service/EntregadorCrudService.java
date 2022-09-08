package totalshake.ciandt.com.dataserviceentregador.domain.service;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.dataserviceentregador.application.controller.request.EntregadorDTOPostRequest;
import totalshake.ciandt.com.dataserviceentregador.application.controller.response.EntregadorDTOResponse;
import totalshake.ciandt.com.dataserviceentregador.application.error.ApiErroCodInternoMensagem;
import totalshake.ciandt.com.dataserviceentregador.application.error.exceptions.EntregadorInexistenteException;
import totalshake.ciandt.com.dataserviceentregador.domain.model.Entregador;
import totalshake.ciandt.com.dataserviceentregador.domain.repository.EntregadorRepository;

import java.util.UUID;

@Service
public class EntregadorCrudService {

    private final EntregadorRepository entregadorRepository;

    public EntregadorCrudService(EntregadorRepository clienteRepository) {
        this.entregadorRepository = clienteRepository;
    }

    public EntregadorDTOResponse salvarEntregador(EntregadorDTOPostRequest entregadorDtoPost){
        var entregador = entregadorDtoPost.toEntregadorModel();
        entregador = entregadorRepository.save(entregador);
        return new EntregadorDTOResponse(entregador.getUuidEntregador(), entregador.getNome());
    }

    public EntregadorDTOResponse buscarEntregadorDto(UUID uuidEntregador){
        var cliente = this.buscarEntregador(uuidEntregador);

        return new EntregadorDTOResponse(
                cliente.getUuidEntregador(),
                cliente.getNome(),
                cliente.getCpf()
        );
    }

    public Entregador buscarEntregador(UUID uuidEntregador) {
       return  entregadorRepository.findById(uuidEntregador)
               .orElseThrow(() -> new EntregadorInexistenteException(
                       ApiErroCodInternoMensagem.DSE002.getCodigo(),
                       ApiErroCodInternoMensagem.DSE002.getMensagem())
               );
    }
}
