package totalshake.ciandt.com.dataservicecliente.application.controller.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public record SaldoParaAdicionarDTORequest(

        @Positive @NotNull
        BigDecimal saldo
) {
}
