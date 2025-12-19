package net.happykoo.ps.application.port.out.persistence;

import net.happykoo.ps.domain.order.Order;

public interface OrderRepository {

  Order save(Order newOrder);

}
