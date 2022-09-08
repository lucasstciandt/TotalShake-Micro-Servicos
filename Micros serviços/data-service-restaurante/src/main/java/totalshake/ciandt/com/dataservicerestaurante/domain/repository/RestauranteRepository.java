package totalshake.ciandt.com.dataservicerestaurante.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import totalshake.ciandt.com.dataservicerestaurante.domain.model.Restaurante;

import java.util.UUID;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, UUID> {
}
