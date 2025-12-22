package net.happykoo.ps.domain.payment;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethod {
  CARD("카드");

  private final String name;

  public static PaymentMethod from(String name) {
    return Arrays.stream(PaymentMethod.values())
        .filter(v -> v.name.equals(name))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
