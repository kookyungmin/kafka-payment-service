package net.happykoo.ps.application.port.in;

import net.happykoo.ps.representation.in.web.request.order.CancelOrder;

public interface PaymentCancelUseCase {

  void paymentCancel(CancelOrder cancelOrder) throws Exception;

}
