package net.happykoo.ps.domain.order;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

  @Override
  public String convertToDatabaseColumn(OrderStatus orderStatus) {
    return orderStatus.name();
  }

  @Override
  public OrderStatus convertToEntityAttribute(String str) {
    return OrderStatus.valueOf(str);
  }
}
