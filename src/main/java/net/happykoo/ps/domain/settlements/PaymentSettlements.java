package net.happykoo.ps.domain.settlements;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.ps.domain.payment.PaymentLedger;
import net.happykoo.ps.domain.payment.PaymentMethod;
import net.happykoo.ps.domain.payment.PaymentMethodConverter;
import net.happykoo.ps.domain.payment.PaymentStatus;

@Entity
@Table(name = "payment_settlements")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PaymentSettlements {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "payment_id")
  private String paymentKey;

  @Convert(converter = PaymentMethodConverter.class)
  private PaymentMethod method;

  @Column(name = "settlements_status")
  private PaymentStatus paymentStatus;

  private int totalAmount;

  private int payOutAmount;

  private int canceledAmount;

  private LocalDate soldDate;

  private LocalDate paidOutDate;

  public PaymentLedger toPaymentLedgerEntity() {
    return PaymentLedger.builder()
        .paymentKey(paymentKey)
        .method(method)
        .paymentStatus(paymentStatus)
        .totalAmount(totalAmount)
        .balanceAmount(totalAmount - canceledAmount)
        .canceledAmount(canceledAmount)
        .payOutAmount(payOutAmount)
        .build();
  }
}
