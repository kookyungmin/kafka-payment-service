package net.happykoo.ps.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.happykoo.ps.domain.payment.PaymentLedger;
import net.happykoo.ps.domain.payment.PaymentMethod;
import net.happykoo.ps.domain.payment.PaymentStatus;
import net.happykoo.ps.infrastructure.out.pg.toss.response.payment.Cancel;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentCancelResponse extends CommonPaymentResponse {

  private List<Cancel> cancels;

  public PaymentLedger toEntity() {
    int canceledTotalAmount = calculateCanceledTotalAmount();

    return PaymentLedger.builder()
        .paymentKey(getPaymentKey())
        .method(PaymentMethod.from(getMethod()))
        .paymentStatus(PaymentStatus.valueOf(getStatus()))
        .totalAmount(getTotalAmount())
        .balanceAmount(getBalanceAmount())
        .canceledAmount(canceledTotalAmount)
        .build();
  }

  private int calculateCanceledTotalAmount() {
    return cancels.stream()
        .map(Cancel::getCancelAmount)
        .reduce(0, Integer::sum);
  }
}
