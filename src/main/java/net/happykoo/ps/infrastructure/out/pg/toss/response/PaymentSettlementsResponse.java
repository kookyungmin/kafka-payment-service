package net.happykoo.ps.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.happykoo.ps.infrastructure.out.pg.toss.response.payment.Cancel;
import net.happykoo.ps.infrastructure.out.pg.toss.response.payment.method.Card;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class PaymentSettlementsResponse extends CommonPaymentResponse {

  private String orderId;
  private String paymentKey;
  private String method;
  @JsonProperty("amount")
  private int totalAmount;
  private Card card;
  private Cancel cancel;
  private int payOutAmount; //지급 금액 (결제 금액 - 수수료)
  private String soldDate;
  private String paidOutDate;

}
