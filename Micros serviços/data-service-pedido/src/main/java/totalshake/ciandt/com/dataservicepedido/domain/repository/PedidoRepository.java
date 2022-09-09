package totalshake.ciandt.com.dataservicepedido.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import totalshake.ciandt.com.dataservicepedido.domain.model.Pedido;

import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
