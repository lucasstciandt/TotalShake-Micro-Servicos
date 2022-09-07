package totalshake.ciandt.com.dataserviceclient.application.controller.response;

import totalshake.ciandt.com.dataserviceclient.domain.model.Cliente;

import java.math.BigDecimal;
import java.util.UUID;

public record ClienteDTOResponse(
        UUID uuid,

        String nome,

        String cpf,

        BigDecimal saldo,

        String rua,

        Integer numeroCasa,

        String cep
) {

    public ClienteDTOResponse(UUID uuid, String nome, BigDecimal saldo) {
        this(uuid, nome, null, saldo, null, null, null);
    }

}
