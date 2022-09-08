package totalshake.ciandt.com.dataservicecliente.application.error.response;

import java.util.List;

public record ErroDtoResponse(
        int httpCode,
        String mensagem,
        String codInterno,
        List<ErroCampoResponseDTO> erros
) {
    public ErroDtoResponse(int value, String message, String codInternoErro) {
        this(value, message, codInternoErro, null);
    }
}
