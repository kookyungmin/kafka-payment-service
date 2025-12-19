package net.happykoo.ps.application.port.in;

import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrder;

public interface CreateNewOrderUseCase {

  Order createOrder(PurchaseOrder newOrder);
}
