package net.happykoo.ps.application.service;

import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.in.CreateNewOrderUseCase;
import net.happykoo.ps.application.port.out.persistence.OrderRepository;
import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateNewOrderUseCase {

  private final OrderRepository orderRepository;

  @Transactional
  @Override
  public Order createOrder(PurchaseOrder newOrder) {
    Order order = newOrder.toEntity();
    order.complete();
    return orderRepository.save(order);
  }
}
