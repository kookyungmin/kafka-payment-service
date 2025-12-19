package net.happykoo.ps.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.happykoo.ps.infrastructure.out.pg.toss.response.payment.method.Card;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentApprovedResponse extends CommonPaymentResponse {

  private String orderName;
  private Card card;
  private String lastTransactionKey;
  private int suppliedAmount;
  private int vat;
  private String requestedAt;
  private String approvedAt;
}
