package net.happykoo.ps.representation.in.web.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.domain.order.OrderItem;
import net.happykoo.ps.domain.order.OrderStatus;

public record PurchaseOrderItem(
    @Min(1)
    int itemIdx,

    UUID productId,

    @NotBlank
    String productName,

    @Min(0)
    int price,

    @Min(1)
    int quantity,

    @Min(0)
    int amounts // price * quantity
) {

  public OrderItem toEntity(Order order) {
    return OrderItem.builder()
        .order(order)
        .itemIdx(itemIdx)
        .productId(productId)
        .productName(productName)
        .price(price)
        .quantity(quantity)
        .size("FREE")
        .state(OrderStatus.ORDER_READY)
        .build();
  }
}
