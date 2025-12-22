package net.happykoo.ps.application.service;

import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.in.PaymentCancelUseCase;
import net.happykoo.ps.application.port.out.api.PaymentApis;
import net.happykoo.ps.application.port.out.persistence.OrderRepository;
import net.happykoo.ps.application.port.out.persistence.PaymentLedgerRepository;
import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.domain.payment.PaymentLedger;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentCancelResponse;
import net.happykoo.ps.representation.in.web.request.order.CancelOrder;
import net.happykoo.ps.representation.in.web.request.payment.PaymentCancel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelService implements PaymentCancelUseCase {

  private final PaymentApis paymentApis;
  private final OrderRepository orderRepository;
  private final PaymentLedgerRepository paymentLedgerRepository;

  @Transactional
  @Override
  public void paymentCancel(CancelOrder cancelOrder) throws Exception {
    Order order = orderRepository.findById(cancelOrder.orderId());
    PaymentLedger latestPaymentLedger = paymentLedgerRepository.findOneByPaymentKeyDesc(
        order.getPaymentId());
    int cancellationAmount = cancelOrder.cancellationAmount();

    if (order.isPurchaseDecision() || !latestPaymentLedger.isCancellableAmountGreaterThan(
        cancellationAmount)) {
      // 구매 확정이거나 남은 취소 가능 금액보다 취소할 금액이 크다면 예외 발생
      throw new Exception("Not Enough CancellationAmount");
    }

    // 취소 API 호출
    PaymentCancelResponse response = paymentApis.requestPaymentCancel(order.getPaymentId(),
        new PaymentCancel(cancelOrder.cancelReason(), cancellationAmount));

    if (paymentApis.isPaymentCanceled(response.getStatus())) {
      paymentLedgerRepository.save(response.toEntity());

      if (cancelOrder.hasItemIdx()) {
        order.cancel(cancelOrder.itemIdxs());
      } else {
        order.cancelAll();
      }
    }
  }
}
