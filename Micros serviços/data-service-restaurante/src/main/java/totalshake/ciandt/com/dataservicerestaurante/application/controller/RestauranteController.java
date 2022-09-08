package totalshake.ciandt.com.dataservicerestaurante.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totalshake.ciandt.com.dataservicerestaurante.application.controller.request.RestauranteDTOPostRequest;
import totalshake.ciandt.com.dataservicerestaurante.application.controller.response.RestauranteDTOResponse;
import totalshake.ciandt.com.dataservicerestaurante.domain.service.RestauranteCrudService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("v1/restaurantes")
public class RestauranteController {

    private final RestauranteCrudService restauranteCrudService;

    public RestauranteController(RestauranteCrudService restauranteCrudService) {
        this.restauranteCrudService = restauranteCrudService;
    }

    @PostMapping
    public ResponseEntity<RestauranteDTOResponse> criarCliente(@RequestBody @Valid RestauranteDTOPostRequest restaurantePost){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restauranteCrudService.salvarRestaurante(restaurantePost));
    }

    @GetMapping("/{uuidRestaurante}")
    public ResponseEntity<RestauranteDTOResponse> buscarCliente(@PathVariable UUID uuidRestaurante){
        return ResponseEntity.ok(restauranteCrudService.buscarRestauranteDtoResponse(uuidRestaurante));
    }

}
