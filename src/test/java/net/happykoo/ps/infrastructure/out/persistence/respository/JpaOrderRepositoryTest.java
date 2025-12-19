package net.happykoo.ps.infrastructure.out.persistence.respository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.representation.in.web.request.order.Orderer;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrder;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
class JpaOrderRepositoryTest {

  @Autowired
  private JpaOrderRepository jpaOrderRepository;

  @Test
  @DisplayName("order 저장 테스트")
  void test1() {
    PurchaseOrder newOrder = new PurchaseOrder(new Orderer("해피쿠", "010-1234-1234"),
        List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
    Order order = newOrder.toEntity();
    order.complete();

    jpaOrderRepository.save(order);

    assertThat(jpaOrderRepository.findById(order.getOrderId()).get())
        .usingRecursiveComparison()
        .isEqualTo(order);
  }

}