package net.happykoo.ps.infrastructure.out.pg.toss.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
*  {
      "transactionKey": "090A796806E726BBB929F4A2CA7DB9A7",
      "cancelReason": "테스트 결제 취소",
      "taxExemptionAmount": 0,
      "canceledAt": "2024-02-13T12:20:23+09:00",
      "easyPayDiscountAmount": 0,
      "receiptKey": null,
      "cancelAmount": 1000,
      "taxFreeAmount": 0,
      "refundableAmount": 0,
      "cancelStatus": "DONE",
      "cancelRequestId": null
    }
    * */

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Cancel {

  private String transactionKey;
  private String cancelReason;
  private int cancelAmount;
  private String cancelStatus;

}
