package totalshake.ciandt.com.dataservicerestaurante.application.controller.response;

import java.util.UUID;

public record RestauranteDTOResponse(
        UUID uuid,

        String nome,

        String cnpj
) {

    public RestauranteDTOResponse(UUID uuid, String nome) {
        this(uuid, nome, null);
    }
}
