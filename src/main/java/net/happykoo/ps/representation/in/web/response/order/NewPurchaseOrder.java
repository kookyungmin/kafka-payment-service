package net.happykoo.ps.representation.in.web.response.order;

import java.util.List;
import java.util.UUID;
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

}
