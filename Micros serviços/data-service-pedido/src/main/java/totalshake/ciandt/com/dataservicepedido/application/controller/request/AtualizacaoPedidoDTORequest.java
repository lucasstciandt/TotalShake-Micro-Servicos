package totalshake.ciandt.com.dataservicepedido.application.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import totalshake.ciandt.com.dataservicepedido.domain.model.Status;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AtualizacaoPedidoDTORequest(
        @NotNull
        Status status,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHoraRealizado,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHoraCancelado,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHoraPago,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHoraConfirmado,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHoraPronto,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHoraSaiuParaEntrega,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHoraEntrega,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHoraPagamentoRecusado
) {

        public AtualizacaoPedidoDTORequest(Status status, LocalDateTime dataHoraRealizado) {
                this(status, dataHoraRealizado, null, null, null,
                        null, null, null, null);
        }
}
