package totalshake.ciandt.com.dataserviceclient.application.error.response;

import java.util.List;

public record ErroDtoResponse(
        int httpCode,
        String mensagem,
        String codInterno,
        List<ErroCampoResponseDTO> erros
) { }
