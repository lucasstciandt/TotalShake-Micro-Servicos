package totalshake.ciandt.com.dataserviceentregador.application.controller.request;

import totalshake.ciandt.com.dataserviceentregador.domain.model.Entregador;

import javax.validation.constraints.*;

public record EntregadorDTOPostRequest(
        @NotEmpty @NotBlank @NotNull @Size(min = 3, max = 200)
        String nome,

        @NotEmpty @NotBlank @NotNull @Size(min = 11, max = 11, message = "CPF deve conter 11 digitos")
        String cpf

) {
        public Entregador toEntregadorModel() {
                var entregador = new Entregador();

                entregador.setNome(this.nome);
                entregador.setCpf(this.cpf);

                return entregador;
        }
}
