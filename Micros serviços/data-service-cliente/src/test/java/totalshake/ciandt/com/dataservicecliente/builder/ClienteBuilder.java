package totalshake.ciandt.com.dataservicecliente.builder;

import totalshake.ciandt.com.dataservicecliente.domain.model.Cliente;
import totalshake.ciandt.com.dataservicecliente.domain.model.Endereco;

import java.math.BigDecimal;

public class ClienteBuilder {

    private Cliente cliente;

    private ClienteBuilder(){}

    public static ClienteBuilder umCliente(){
        var clienteBuilder = new ClienteBuilder();
        clienteBuilder.cliente = new Cliente();
        clienteBuilder.cliente.setCpf("12345678911");
        clienteBuilder.cliente.setNome("Norberto Siqueira");
        clienteBuilder.cliente.setSaldo(new BigDecimal("0.00"));

        return clienteBuilder;
    }

    public ClienteBuilder comEndereco(){
        var endereco = new Endereco();
        endereco.setNumero(33);
        endereco.setCep("80809090");
        endereco.setRua("Alabama Shore Avenue");

        cliente.adicionarEndereco(endereco);

        return this;
    }
    public Cliente build(){
        return this.cliente;
    }
}
