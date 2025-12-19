package net.happykoo.ps.infrastructure.out.persistence.respository;

import net.happykoo.ps.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderItemRepository extends JpaRepository<OrderItem, Long> {

}
