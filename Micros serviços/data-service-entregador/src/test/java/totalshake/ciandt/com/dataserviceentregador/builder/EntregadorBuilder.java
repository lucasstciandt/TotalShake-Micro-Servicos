package totalshake.ciandt.com.dataserviceentregador.builder;

import totalshake.ciandt.com.dataserviceentregador.domain.model.Entregador;

public class EntregadorBuilder {

    private Entregador entregador;

    private EntregadorBuilder(){}

    public static EntregadorBuilder umEntregador(){
        var entregadorBuilder = new EntregadorBuilder();
        entregadorBuilder.entregador = new Entregador();
        entregadorBuilder.entregador.setNome("Total Shake Av.Paulista");
        entregadorBuilder.entregador.setCpf("12345678911");

        return entregadorBuilder;
    }

    public Entregador build(){
        return this.entregador;
    }
}
