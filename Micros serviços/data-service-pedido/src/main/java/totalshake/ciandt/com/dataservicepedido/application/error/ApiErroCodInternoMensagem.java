package totalshake.ciandt.com.dataservicepedido.application.error;

public enum ApiErroCodInternoMensagem {

    DSP001("DSP-001", "Um ou mais campos inválidos"),
    DSP002("DSP-002","Recurso Inexistente" );

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
