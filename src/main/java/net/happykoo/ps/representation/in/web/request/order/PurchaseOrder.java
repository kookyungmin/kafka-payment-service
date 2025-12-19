package net.happykoo.ps.representation.in.web.request.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;
import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.domain.order.OrderItem;

public record PurchaseOrder(
    @NotNull(message = "The orderer is required.")
    @Valid
    Orderer orderer,

    @Size(min = 1)
    @Valid
    List<PurchaseOrderItem> newlyOrderedItem
) {

  public Order toEntity() {
    Order order = Order.builder()
        .name(orderer.name())
        .phoneNumber(orderer.phoneNumber())
        .build();
    List<OrderItem> items = newlyOrderedItem.stream()
        .map(i -> i.toEntity(order))
        .collect(Collectors.toList());

    order.addItems(items);

    return order;
  }
}
