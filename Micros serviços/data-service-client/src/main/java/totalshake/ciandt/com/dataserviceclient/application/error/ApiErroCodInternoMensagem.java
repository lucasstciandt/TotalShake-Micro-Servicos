package totalshake.ciandt.com.dataserviceclient.application.error;

public enum ApiErroCodInternoMensagem {

    DSC001("DSC-001", "Um ou mais campos inválidos"),
    ;

    private final String codigo;
    private final String mensagem;

    ApiErroCodInternoMensagem(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
