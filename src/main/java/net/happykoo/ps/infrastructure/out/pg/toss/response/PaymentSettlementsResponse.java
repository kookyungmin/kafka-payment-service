package net.happykoo.ps.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.happykoo.ps.domain.payment.PaymentMethod;
import net.happykoo.ps.domain.payment.PaymentStatus;
import net.happykoo.ps.domain.settlements.PaymentSettlements;
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

  public PaymentSettlements toEntity() {
    return PaymentSettlements.builder()
        .paymentKey(paymentKey)
        .paymentStatus(this.converterToEntityAttribute())
        .method(PaymentMethod.from(method))
        .totalAmount(totalAmount)
        .canceledAmount(cancel == null ? 0 : cancel.getCancelAmount())
        .payOutAmount(payOutAmount)
        .soldDate(LocalDate.parse(soldDate))
        .paidOutDate(LocalDate.parse(paidOutDate))
        .build();
  }

  private PaymentStatus converterToEntityAttribute() {
    switch (card.getAcquireStatus()) {
      case "READY":
      case "REQUESTED":
        return PaymentStatus.valueOf("SETTLEMENTS_REQUESTED");
      case "COMPLETED":
        return PaymentStatus.valueOf("SETTLEMENTS_COMPLETED");
      case "CANCEL_REQUESTED":
      case "CANCELED":
        return PaymentStatus.valueOf("SETTLEMENTS_CANCELED");
      default:
        return PaymentStatus.valueOf("SETTLEMENTS_REQUESTED");
    }

  }
}
