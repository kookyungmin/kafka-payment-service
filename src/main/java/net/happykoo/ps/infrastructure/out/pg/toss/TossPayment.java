package net.happykoo.ps.infrastructure.out.pg.toss;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.out.api.PaymentApis;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentApprovedResponse;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentCancelResponse;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentSettlementsResponse;
import net.happykoo.ps.representation.in.web.request.payment.PaymentApproved;
import net.happykoo.ps.representation.in.web.request.payment.PaymentCancel;
import net.happykoo.ps.representation.in.web.request.payment.PaymentSettlement;
import org.springframework.stereotype.Component;
import retrofit2.Response;

@Component
@RequiredArgsConstructor
public class TossPayment implements PaymentApis {

  private final TossPaymentApis tossClient;

  @Override
  public PaymentApprovedResponse requestPaymentApprove(PaymentApproved paymentInfo)
      throws IOException {
    Response<PaymentApprovedResponse> response = tossClient.paymentFulfill(paymentInfo).execute();
    if (response.isSuccessful()) {
      return response.body();
    }

    throw new IOException(response.message());
  }

  @Override
  public boolean isPaymentApproved(String status) {
    return "DONE".equalsIgnoreCase(status);
  }

  @Override
  public PaymentCancelResponse requestPaymentCancel(String paymentKey,
      PaymentCancel cancelMessage) throws IOException {
    Response<PaymentCancelResponse> response = tossClient.paymentCancel(paymentKey, cancelMessage)
        .execute();

    if (response.isSuccessful()) {
      return response.body();
    }

    throw new IOException(response.message());
  }

  @Override
  public List<PaymentSettlementsResponse> requestPaymentSettlement(
      PaymentSettlement paymentSettlement) throws IOException {
    Response<List<PaymentSettlementsResponse>> response = tossClient.paymentSettlements(
        paymentSettlement.getStartDate(),
        paymentSettlement.getEndDate(),
        paymentSettlement.getPage(),
        paymentSettlement.getSize()
    ).execute();

    if (response.isSuccessful()) {
      return response.body();
    }

    throw new IOException(response.message());
  }
}
