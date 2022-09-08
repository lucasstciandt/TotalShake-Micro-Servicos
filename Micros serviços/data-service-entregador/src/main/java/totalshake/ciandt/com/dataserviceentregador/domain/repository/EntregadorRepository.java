package totalshake.ciandt.com.dataserviceentregador.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import totalshake.ciandt.com.dataserviceentregador.domain.model.Entregador;

import java.util.UUID;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, UUID> {
}
