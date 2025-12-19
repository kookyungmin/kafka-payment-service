package net.happykoo.ps.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.domain.order.OrderStatus;
import net.happykoo.ps.representation.in.web.request.order.Orderer;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrder;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 주문 업무의 모든 업무 규칙 기능(비즈니스 로직) 제공하는 클래스
class OrderTests {

  @Test
  @DisplayName("신규 상품 주문 :: 상품 주문은 최소 1개 이상 주문해야 한다.")
  void test1_1() {
    PurchaseOrder newOrder = new PurchaseOrder(new Orderer("해피쿠", "010-1234-1234"),
        List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500),
            new PurchaseOrderItem(2, UUID.randomUUID(), "농심 사리곰탕 4봉", 4500, 2, 9000)));
    Order order = newOrder.toEntity();

    order.complete();

    assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER_COMPLETED);
    assertThat(order.getTotalPrice()).isEqualTo(13500);
    order.getItems()
        .forEach(item -> assertThat(item.getState()).isEqualTo(OrderStatus.ORDER_COMPLETED));
  }

  @Test
  @DisplayName("신규 상품 주문 :: 0개인 경우 예외 발생")
  void test1_2() {
    PurchaseOrder newOrder = new PurchaseOrder(new Orderer("해피쿠", "010-1234-1234"),
        Collections.emptyList());
    Order order = newOrder.toEntity();

    assertThrows(IllegalArgumentException.class, order::complete);
  }

  @Test
  @DisplayName("신규 상품 주문 :: 상품 주문시, product_id 중복 시 예외 처리")
  void test2_1() {
    UUID productId = UUID.randomUUID();
    PurchaseOrder newOrder = new PurchaseOrder(new Orderer("해피쿠", "010-1234-1234"),
        List.of(new PurchaseOrderItem(1, productId, "농심 짜파게티 4봉", 4500, 1, 4500),
            new PurchaseOrderItem(2, productId, "농심 사리곰탕 4봉", 4500, 2, 9000)));
    Order order = newOrder.toEntity();

    assertThrows(IllegalArgumentException.class, order::complete);
  }

  @Test
  @DisplayName("상품 취소 :: 구매완료 상태가 아닌 주문 건에 대해서만 취소가 가능하다.")
  void test3_1() {

  }

  @Test
  @DisplayName("상품 취소 :: 구매완료 상태인데, 취소하면 예외 발생")
  void test3_2() {

  }

  @Test
  @DisplayName("상품 취소 :: 상품 정보(itemIdx) 가 있는 경우 부분 취소")
  void test4_1() {

  }

  @Test
  @DisplayName("상품 취소 :: 상품 정보(itemIdx) 가 없는 경우 전체 취소")
  void test4_2() {

  }
}
