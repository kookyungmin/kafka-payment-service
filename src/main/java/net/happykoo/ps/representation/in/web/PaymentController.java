package net.happykoo.ps.representation.in.web;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.in.PaymentFulfillUseCase;
import net.happykoo.ps.representation.in.web.request.payment.PaymentApproved;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentFulfillUseCase paymentFulfillUseCase;

  @GetMapping("/success")
  public String paymentFulfil(@RequestParam(value = "paymentType") String paymentType,
      @RequestParam(value = "orderId") String orderId,
      @RequestParam(value = "paymentKey") String paymentKey,
      @RequestParam(value = "amount") String amount) {
    return "success";
  }

  @GetMapping("/fail")
  public String paymentFail(@RequestParam(value = "message") String message) {
    return "fail";
  }

  @PostMapping("/confirm")
  public String paymentConfirm(@RequestBody PaymentApproved paymentInfo) throws IOException {
    boolean isSuccess = paymentFulfillUseCase.paymentApproved(paymentInfo);

    if (isSuccess) {
      return "success";
    } else {
      return "fail";
    }
  }
}
