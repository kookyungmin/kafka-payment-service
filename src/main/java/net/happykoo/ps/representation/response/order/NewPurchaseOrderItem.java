package net.happykoo.ps.representation.response.order;

import java.util.UUID;
import net.happykoo.ps.domain.order.OrderStatus;

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

}
