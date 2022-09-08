package totalshake.ciandt.com.dataservicerestaurante.application.error;

public enum ApiErroCodInternoMensagem {

    DSR001("DSR-001", "Um ou mais campos inválidos"),
    DSR002("DSR-002","Recurso Inexistente");

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
