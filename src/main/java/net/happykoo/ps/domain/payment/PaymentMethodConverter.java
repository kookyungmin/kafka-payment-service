package net.happykoo.ps.domain.payment;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {

  @Override
  public String convertToDatabaseColumn(PaymentMethod paymentMethod) {
    return paymentMethod.getName();
  }

  @Override
  public PaymentMethod convertToEntityAttribute(String name) {
    return PaymentMethod.from(name);
  }
}
