package totalshake.ciandt.com.dataserviceentregador.application.controller.response;

import java.math.BigDecimal;
import java.util.UUID;

public record EntregadorDTOResponse(
        UUID uuid,

        String nome,

        String cpf
) {

    public EntregadorDTOResponse(UUID uuid, String nome) {
        this(uuid, nome, null);
    }

}
