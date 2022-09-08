package totalshake.ciandt.com.dataserviceentregador.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ENTREGADOR")
public class Entregador {

    @Id
    @GeneratedValue
    @Column(name = "uuid_entregador")
    private UUID uuidEntregador = UUID.randomUUID();

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    public UUID getUuidEntregador() {
        return uuidEntregador;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
