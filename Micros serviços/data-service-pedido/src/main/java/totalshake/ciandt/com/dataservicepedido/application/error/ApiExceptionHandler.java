package totalshake.ciandt.com.dataservicepedido.application.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import totalshake.ciandt.com.dataservicepedido.application.error.exceptions.PedidoInexistenteException;
import totalshake.ciandt.com.dataservicepedido.application.error.response.ErroDTOCampoResponse;
import totalshake.ciandt.com.dataservicepedido.application.error.response.ErroDTOResponse;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDTOResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex ,
                                                                                 WebRequest request) {
        ErroDTOResponse error = new ErroDTOResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ApiErroCodInternoMensagem.DSP001.getMensagem(),
                ApiErroCodInternoMensagem.DSP001.getCodigo(),
                ex.getBindingResult().getFieldErrors().stream()
                        .map(errorField -> new ErroDTOCampoResponse(
                                errorField.getDefaultMessage(),
                                errorField.getField()
                        ))
                        .collect(Collectors.toList())
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(PedidoInexistenteException.class)
    public ResponseEntity<ErroDTOResponse> handlePedidoInexistenteException(PedidoInexistenteException ex, WebRequest request) {
        ErroDTOResponse error = new ErroDTOResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                ex.getCodInternoErro()
        );
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
