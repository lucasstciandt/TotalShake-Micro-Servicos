package totalshake.ciandt.com.dataservicerestaurante.domain.service;

import org.springframework.stereotype.Service;
import totalshake.ciandt.com.dataservicerestaurante.application.controller.request.RestauranteDTOPostRequest;
import totalshake.ciandt.com.dataservicerestaurante.application.controller.response.RestauranteDTOResponse;
import totalshake.ciandt.com.dataservicerestaurante.application.error.ApiErroCodInternoMensagem;
import totalshake.ciandt.com.dataservicerestaurante.application.error.exceptions.RestauranteInexistenteException;
import totalshake.ciandt.com.dataservicerestaurante.domain.model.Restaurante;
import totalshake.ciandt.com.dataservicerestaurante.domain.repository.RestauranteRepository;

import java.util.UUID;

@Service
public class RestauranteCrudService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteCrudService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public RestauranteDTOResponse salvarRestaurante(RestauranteDTOPostRequest restauranteDtoPost){
        var restaurante = restauranteDtoPost.toRestauranteModel();
        restaurante = restauranteRepository.save(restaurante);
        return new RestauranteDTOResponse(restaurante.getUuidRestaurante(), restaurante.getNome());
    }

    public RestauranteDTOResponse buscarRestauranteDtoResponse(UUID uuidCliente){
        var restaurante = this.buscarRestaurante(uuidCliente);

        return new RestauranteDTOResponse(
                restaurante.getUuidRestaurante(),
                restaurante.getNome(),
                restaurante.getCnpj()
        );
    }

    public Restaurante buscarRestaurante(UUID uuidRestaurante) {
        return  restauranteRepository.findById(uuidRestaurante)
                .orElseThrow(() -> new RestauranteInexistenteException(
                        ApiErroCodInternoMensagem.DSR002.getCodigo(),
                        ApiErroCodInternoMensagem.DSR002.getMensagem())
                );
    }
}
