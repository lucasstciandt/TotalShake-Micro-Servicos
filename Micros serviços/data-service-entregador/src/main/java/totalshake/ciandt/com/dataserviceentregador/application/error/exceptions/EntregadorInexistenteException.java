package totalshake.ciandt.com.dataserviceentregador.application.error.exceptions;

public class EntregadorInexistenteException extends RuntimeException {

    private final String codInternoErro;
    private final String mensagem;

    public EntregadorInexistenteException(String codInternoErro, String mensagem){
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
