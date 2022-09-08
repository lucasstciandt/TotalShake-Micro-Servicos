package totalshake.ciandt.com.dataservicecliente.application.error.exceptions;

public class ClienteInexistenteException extends RuntimeException {

    private final String codInternoErro;
    private final String mensagem;

    public ClienteInexistenteException(String codInternoErro, String mensagem){
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
