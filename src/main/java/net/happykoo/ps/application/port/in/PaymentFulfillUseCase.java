package net.happykoo.ps.application.port.in;

import java.io.IOException;
import net.happykoo.ps.representation.in.web.request.payment.PaymentApproved;

public interface PaymentFulfillUseCase {

  boolean paymentApproved(PaymentApproved paymentInfo) throws IOException;

}
