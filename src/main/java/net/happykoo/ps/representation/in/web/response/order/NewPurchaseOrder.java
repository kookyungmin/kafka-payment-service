package net.happykoo.ps.representation.in.web.response.order;

import java.util.List;
import java.util.UUID;
import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.domain.order.OrderItem;
import net.happykoo.ps.domain.order.OrderStatus;
import net.happykoo.ps.representation.in.web.request.order.Orderer;

public record NewPurchaseOrder(
    UUID orderId,
    Orderer orderer,
    String paymentId,
    int totalPrice,
    OrderStatus status,
    List<NewPurchaseOrderItem> items

) {

  private NewPurchaseOrder(UUID id, String name, String phoneNumber, String paymentId,
      int totalPrice, OrderStatus status, List<OrderItem> items) {
    this(id, new Orderer(name, phoneNumber), paymentId, totalPrice, status,
        NewPurchaseOrderItem.from(items));
  }

  public static NewPurchaseOrder from(Order order) {
    return new NewPurchaseOrder(order.getOrderId(),
        order.getName(),
        order.getPhoneNumber(),
        order.getPaymentId(),
        order.getTotalPrice(),
        order.getStatus(),
        order.getItems());
  }
}
