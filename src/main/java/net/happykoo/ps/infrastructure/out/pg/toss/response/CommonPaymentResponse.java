package net.happykoo.ps.infrastructure.out.pg.toss.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class CommonPaymentResponse {

  private String orderId;
  private String paymentKey;
  private String method;
  private String status;
  private int totalAmount;
  private int balanceAmount;
}
