package totalshake.ciandt.com.dataserviceclient.application.controller.request;

import totalshake.ciandt.com.dataserviceclient.domain.model.Cliente;
import totalshake.ciandt.com.dataserviceclient.domain.model.Endereco;

import javax.validation.constraints.*;

public record ClienteDTOPostRequest (
        @NotEmpty @NotBlank @NotNull @Size(min = 3, max = 200)
        String nome,

        @NotEmpty @NotBlank @NotNull @Size(min = 11, max = 11, message = "CPF deve conter 11 digitos")
        String cpf,

        @NotEmpty @NotNull @Size(min = 3, max = 200)
        String rua,

        @Positive @NotNull
        int numeroCasa,

        @NotEmpty @NotBlank @NotNull @Size(min = 8, max = 8)
        String cep
) {
        public Cliente toClienteModel() {
                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();

                cliente.setNome(this.nome);
                cliente.setCpf(this.cpf);

                endereco.setRua(this.rua);
                endereco.setNumero(this.numeroCasa);
                endereco.setCep(this.cep);

                cliente.adicionarEndereco(endereco);

                return cliente;
        }
}
