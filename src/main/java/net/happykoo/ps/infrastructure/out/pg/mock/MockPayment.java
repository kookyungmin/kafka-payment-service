package net.happykoo.ps.infrastructure.out.pg.mock;

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

@Component("mockPayment")
@RequiredArgsConstructor
public class MockPayment implements PaymentApis {

  private final MockPaymentApis mockPaymentApis;

  @Override
  public List<PaymentSettlementsResponse> requestPaymentSettlement(
      PaymentSettlement paymentSettlement) throws IOException {
    Response<List<PaymentSettlementsResponse>> response = mockPaymentApis.paymentSettlements()
        .execute();
    if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
      return response.body();
    }

    throw new IOException(response.message());
  }

  @Override
  public PaymentApprovedResponse requestPaymentApprove(PaymentApproved requestMessage)
      throws IOException {
    return null;
  }

  @Override
  public boolean isPaymentApproved(String status) {
    return false;
  }

  @Override
  public PaymentCancelResponse requestPaymentCancel(String paymentKey, PaymentCancel cancelMessage)
      throws IOException {
    return null;
  }

  @Override
  public boolean isPaymentCanceled(String status) {
    return false;
  }
}
