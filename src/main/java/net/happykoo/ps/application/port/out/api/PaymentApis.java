package net.happykoo.ps.application.port.out.api;

import java.io.IOException;
import java.util.List;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentApprovedResponse;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentCancelResponse;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentSettlementsResponse;
import net.happykoo.ps.representation.in.web.request.payment.PaymentApproved;
import net.happykoo.ps.representation.in.web.request.payment.PaymentCancel;
import net.happykoo.ps.representation.in.web.request.payment.PaymentSettlement;

public interface PaymentApis {

  PaymentApprovedResponse requestPaymentApprove(PaymentApproved requestMessage) throws IOException;

  boolean isPaymentApproved(String status);

  PaymentCancelResponse requestPaymentCancel(String paymentKey, PaymentCancel cancelMessage)
      throws IOException;

  List<PaymentSettlementsResponse> requestPaymentSettlement(PaymentSettlement paymentSettlement)
      throws IOException;

  boolean isPaymentCanceled(String status);
}
