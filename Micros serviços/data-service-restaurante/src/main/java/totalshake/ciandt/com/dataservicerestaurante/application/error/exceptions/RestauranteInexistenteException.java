package totalshake.ciandt.com.dataservicerestaurante.application.error.exceptions;

public class RestauranteInexistenteException extends RuntimeException {

    private final String codInternoErro;
    private final String mensagem;

    public RestauranteInexistenteException(String codInternoErro, String mensagem){
        this.codInternoErro = codInternoErro;
        this.mensagem = mensagem;
    }

    public String getCodInternoErro() {
        return codInternoErro;
    }

    @Override
    public String getMessage() {
        return mensagem;
    }
}
