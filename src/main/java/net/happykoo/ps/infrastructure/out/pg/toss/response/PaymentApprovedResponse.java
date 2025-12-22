package net.happykoo.ps.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.happykoo.ps.domain.payment.PaymentLedger;
import net.happykoo.ps.domain.payment.PaymentMethod;
import net.happykoo.ps.domain.payment.PaymentStatus;
import net.happykoo.ps.domain.payment.TransactionType;
import net.happykoo.ps.domain.payment.card.AcquireStatus;
import net.happykoo.ps.domain.payment.card.CardPayment;
import net.happykoo.ps.infrastructure.out.pg.toss.response.payment.method.Card;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentApprovedResponse extends CommonPaymentResponse {

  private String orderName;
  private Card card;
  private String lastTransactionKey;
  private int suppliedAmount;
  private int vat;
  private String requestedAt;
  private String approvedAt;

  public TransactionType toTransactionTypeEntity() {
    switch (PaymentMethod.from(getMethod())) {
      case CARD -> {
        return toCardPaymentEntity();
      }
      default -> throw new IllegalArgumentException("not supported payment method.");
    }
  }

  private TransactionType toCardPaymentEntity() {
    return CardPayment.builder()
        .paymentKey(getPaymentKey())
        .cardNumber(card.getNumber())
        .approveNo(card.getApproveNo())
        .acquireStatus(AcquireStatus.valueOf(card.getAcquireStatus()))
        .acquirerCode(card.getAcquirerCode())
        .acquirerStatus(card.getAcquireStatus())
        .build();
  }

  public PaymentLedger toPaymentLedgerEntity() {
    return PaymentLedger.builder()
        .paymentKey(getPaymentKey())
        .method(PaymentMethod.from(getMethod()))
        .paymentStatus(PaymentStatus.valueOf(this.getStatus()))
        .totalAmount(this.getTotalAmount())
        .balanceAmount(this.getBalanceAmount())
        .canceledAmount(0)
        .build();
  }
}
