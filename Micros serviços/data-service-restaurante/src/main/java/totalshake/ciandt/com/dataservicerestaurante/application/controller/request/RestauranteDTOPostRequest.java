package totalshake.ciandt.com.dataservicerestaurante.application.controller.request;


import totalshake.ciandt.com.dataservicerestaurante.domain.model.Restaurante;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record RestauranteDTOPostRequest(
        @NotEmpty @NotBlank @NotNull @Size(min = 3, max = 200)
        String nome,

        @NotEmpty @NotBlank @NotNull @Size(min = 14, max = 14, message = "CNPJ deve conter 14 digitos")
        String cnpj

) {
        public Restaurante toRestauranteModel() {
                var restaurante = new Restaurante();
                restaurante.setNome(this.nome);
                restaurante.setCnpj(this.cnpj);

                return restaurante;
        }
}
