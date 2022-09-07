package totalshake.ciandt.com.dataserviceclient.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String rua;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false, length = 8)
    private String cep;

    @OneToOne
    @JoinColumn(name = "uuid_cliente")
    private Cliente cliente;


    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
