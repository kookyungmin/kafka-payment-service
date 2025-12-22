package net.happykoo.ps.application.port.out.persistence;

import java.util.UUID;
import net.happykoo.ps.domain.order.Order;

public interface OrderRepository {

  Order save(Order newOrder);

  Order findById(UUID orderId);

}
