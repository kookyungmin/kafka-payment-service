package net.happykoo.ps.infrastructure.out.persistence.respository;

import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.out.persistence.OrderRepository;
import net.happykoo.ps.domain.order.Order;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

  private final JpaOrderRepository jpaOrderRepository;

  @Override
  public Order save(Order newOrder) {
    return jpaOrderRepository.save(newOrder);
  }
}
