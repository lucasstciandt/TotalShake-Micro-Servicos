package totalshake.ciandt.com.dataservicerestaurante.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "RESTAURANTE")
public class Restaurante {

    @Id
    @GeneratedValue
    @Column(name = "uuid_restaurante")
    private UUID uuidRestaurante = UUID.randomUUID();

    @Column(length = 150)
    private String nome;

    @Column(length = 14)
    private String cnpj;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public UUID getUuidRestaurante() {
        return uuidRestaurante;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }
}
