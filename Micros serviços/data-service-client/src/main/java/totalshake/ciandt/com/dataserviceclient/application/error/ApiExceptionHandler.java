package totalshake.ciandt.com.dataserviceclient.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import totalshake.ciandt.com.dataserviceclient.application.error.response.ErroCampoResponseDTO;
import totalshake.ciandt.com.dataserviceclient.application.error.response.ErroDtoResponse;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDtoResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex ,
                                                                                      WebRequest request) {
        ErroDtoResponse error = new ErroDtoResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ApiErroCodInternoMensagem.DSC001.getMensagem(),
                ApiErroCodInternoMensagem.DSC001.getCodigo(),
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(errorField ->
                                new ErroCampoResponseDTO(
                                        errorField.getDefaultMessage(),
                                        errorField.getField()
                                )
                        )
                        .collect(Collectors.toList())
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
}
