package net.happykoo.ps.domain.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//거래 원장 엔티티

@Entity
@Table(name = "payment_transaction")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentLedger {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "payment_id")
  private String paymentKey;

  @Convert(converter = PaymentMethodConverter.class)
  private PaymentMethod method;

  @Enumerated(value = EnumType.STRING)
  private PaymentStatus paymentStatus;

  private int totalAmount;
  private int payOutAmount;
  private int balanceAmount; //정산되지 않은 남아있는 금액

  private int canceledAmount; //취소 금액

  public boolean isCancellableAmountGreaterThan(int cancellationAmount) {
    return balanceAmount >= cancellationAmount;
  }
}
