package totalshake.ciandt.com.dataservicerestaurante.domain.model;

import javax.persistence.*;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(uuidRestaurante, that.uuidRestaurante) && Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuidRestaurante, cnpj);
    }
}
