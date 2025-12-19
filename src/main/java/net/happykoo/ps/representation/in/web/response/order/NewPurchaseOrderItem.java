package net.happykoo.ps.representation.in.web.response.order;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import net.happykoo.ps.domain.order.OrderItem;
import net.happykoo.ps.domain.order.OrderStatus;

@Builder
public record NewPurchaseOrderItem(
    UUID orderId,
    int itemIdx,
    UUID productId,
    String productName,
    int price,
    String size,
    int amount,
    int quantity,
    OrderStatus itemStatus
) {

  public static List<NewPurchaseOrderItem> from(List<OrderItem> items) {
    return items.stream()
        .map(orderItem ->
            NewPurchaseOrderItem.builder()
                .orderId(orderItem.getOrder().getOrderId())
                .itemIdx(orderItem.getItemIdx())
                .productName(orderItem.getProductName())
                .price(orderItem.getPrice())
                .size(orderItem.getSize())
                .amount(orderItem.getAmount())
                .quantity(orderItem.getQuantity())
                .itemStatus(orderItem.getState())
                .build())
        .toList();
  }
}
