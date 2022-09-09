package totalshake.ciandt.com.dataservicepedido.application.error.response;

import java.util.List;

public record ErroDTOResponse(
        int httpCode,
        String mensagem,
        String codInterno,
        List<ErroDTOCampoResponse> erros
) {
    public ErroDTOResponse(int value, String message, String codInternoErro) {
        this(value, message, codInternoErro, null);
    }
}
