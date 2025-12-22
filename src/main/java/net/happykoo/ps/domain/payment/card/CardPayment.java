package net.happykoo.ps.domain.payment.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.happykoo.ps.domain.payment.TransactionType;

@Entity
@Table(name = "card_payment")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardPayment extends TransactionType {

  @Id
  @Column(name = "payment_id")
  private String paymentKey;

  private String cardNumber;

  private String approveNo;

  @Enumerated(value = EnumType.STRING)
  private AcquireStatus acquireStatus;

  private String issuerCode;

  private String acquirerCode;

  private String acquirerStatus;

}
