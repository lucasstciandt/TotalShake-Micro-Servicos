package totalshake.ciandt.com.dataserviceclient.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue
    @Column(name = "uuid_cliente")
    private UUID uuidCliente = UUID.randomUUID();

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    private BigDecimal saldo = new BigDecimal("0.00");

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "cliente"
    )
    private Endereco endereco;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public UUID getUuidCliente() {
        return uuidCliente;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        this.garantirNullSafetyEndereco();
        return endereco;
    }

    public void adicionarEndereco(Endereco endereco) {
        this.garantirNullSafetyEndereco();
        this.endereco = endereco;
        endereco.setCliente(this);
    }

    private void garantirNullSafetyEndereco() {
        if(this.endereco == null){
            this.endereco = new Endereco();
        }
    }
}
