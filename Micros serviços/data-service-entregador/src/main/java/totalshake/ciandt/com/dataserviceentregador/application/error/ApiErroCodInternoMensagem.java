package totalshake.ciandt.com.dataserviceentregador.application.error;

public enum ApiErroCodInternoMensagem {

    DSE001("DSE-001", "Um ou mais campos inválidos"),
    DSE002("DSE-002","Recurso Inexistente" );

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
