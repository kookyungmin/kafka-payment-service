package net.happykoo.ps.infrastructure.out.persistence.respository;

import java.util.UUID;
import net.happykoo.ps.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, UUID> {

}
