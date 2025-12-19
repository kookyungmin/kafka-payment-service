package net.happykoo.ps.application.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.UUID;
import net.happykoo.ps.application.port.out.persistence.OrderRepository;
import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.representation.in.web.request.order.Orderer;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrder;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;

  @InjectMocks
  private OrderService orderService;

  @Test
  @DisplayName("createOrder 테스트")
  void test1() {
    PurchaseOrder newOrder = new PurchaseOrder(new Orderer("해피쿠", "010-1234-1234"),
        List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
    Order order = newOrder.toEntity();

    Mockito.when(orderRepository.save(any()))
        .thenReturn(order);

    Order completedOrder = orderService.createOrder(newOrder);

    Mockito.verify(orderRepository, Mockito.times(1)).save(any());
    Assertions.assertEquals(order, completedOrder);
  }
}