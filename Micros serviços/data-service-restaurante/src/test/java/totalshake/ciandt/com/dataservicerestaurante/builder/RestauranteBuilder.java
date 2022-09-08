package totalshake.ciandt.com.dataservicerestaurante.builder;

import totalshake.ciandt.com.dataservicerestaurante.domain.model.Restaurante;

public class RestauranteBuilder {

    private Restaurante restaurante;

    private RestauranteBuilder(){}

    public static RestauranteBuilder umRestaurante(){
        var restauranteBuilder = new RestauranteBuilder();
        restauranteBuilder.restaurante = new Restaurante();
        restauranteBuilder.restaurante.setNome("Total Shake Av.Paulista");
        restauranteBuilder.restaurante.setCnpj("12345678911234");

        return restauranteBuilder;
    }

    public Restaurante build(){
        return this.restaurante;
    }
}
