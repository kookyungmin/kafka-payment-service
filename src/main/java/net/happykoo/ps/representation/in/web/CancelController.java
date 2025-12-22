package net.happykoo.ps.representation.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.in.PaymentCancelUseCase;
import net.happykoo.ps.representation.in.web.request.order.CancelOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class CancelController {

  private final PaymentCancelUseCase paymentCancelUseCase;

  @PostMapping("/cancel")
  public void cancelPayment(@RequestBody @Valid CancelOrder cancelOrder) throws Exception {
    paymentCancelUseCase.paymentCancel(cancelOrder);
  }
}
