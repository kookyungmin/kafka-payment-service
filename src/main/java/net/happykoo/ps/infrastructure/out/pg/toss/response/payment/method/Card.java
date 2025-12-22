package net.happykoo.ps.infrastructure.out.pg.toss.response.payment.method;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
  "card": {
    "issuerCode": "71",
    "acquirerCode": "71",
    "number": "12345678****000*",
    "installmentPlanMonths": 0,
    "isInterestFree": false,
    "interestPayer": null,
    "approveNo": "00000000",
    "useCardPoint": false,
    "cardType": "신용",
    "ownerType": "개인",
    "acquireStatus": "READY",
    "receiptUrl": "https://dashboard.tosspayments.com/receipt/redirection?transactionId=tviva20240213121757MvuS8&ref=PX",
    "amount": 1000
  },
*/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true) //값이 없어도 무시
public class Card {

  private String issuerCode;
  private String acquirerCode;
  private String number;
  private String cardType;
  private String acquireStatus;
  private String approveNo;
  private int amount;
  private boolean isInterestFree;
  private String receiptUrl;

}
