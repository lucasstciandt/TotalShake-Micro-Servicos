package totalshake.ciandt.com.dataservicecliente.application.error.exceptions;

public class ValorInvalidoException extends RuntimeException {

    private final String codInternoErro;
    private final String mensagem;

    public ValorInvalidoException(String codInternoErro, String mensagem){
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
