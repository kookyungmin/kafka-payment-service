package net.happykoo.ps.application.service;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import net.happykoo.ps.application.port.in.PaymentFulfillUseCase;
import net.happykoo.ps.application.port.out.api.PaymentApis;
import net.happykoo.ps.application.port.out.persistence.OrderRepository;
import net.happykoo.ps.application.port.out.persistence.PaymentLedgerRepository;
import net.happykoo.ps.application.port.out.persistence.TransactionTypeRepository;
import net.happykoo.ps.domain.order.Order;
import net.happykoo.ps.domain.payment.PaymentMethod;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentApprovedResponse;
import net.happykoo.ps.representation.in.web.request.payment.PaymentApproved;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService implements PaymentFulfillUseCase {

  private final PaymentApis paymentApis;
  private final OrderRepository orderRepository;
  private final PaymentLedgerRepository paymentLedgerRepository;
  //모든 TransactionTypeRepository 빈 Set 으로 주입 됨
  private final Set<TransactionTypeRepository> transactionTypeRepositorySet;

  public PaymentService(@Qualifier("tossPayment") PaymentApis paymentApis,
      OrderRepository orderRepository,
      PaymentLedgerRepository paymentLedgerRepository,
      Set<TransactionTypeRepository> transactionTypeRepositorySet) {
    this.paymentApis = paymentApis;
    this.orderRepository = orderRepository;
    this.paymentLedgerRepository = paymentLedgerRepository;
    this.transactionTypeRepositorySet = transactionTypeRepositorySet;
  }


  @Override
  @Transactional
  public boolean paymentApproved(PaymentApproved paymentInfo) throws IOException {
    // 주문이 주문완료 상태인지 검증
    Order order = orderRepository.findById(UUID.fromString(paymentInfo.orderId()));

    if (!order.isCompleted()) {
      throw new IllegalArgumentException(
          "Order is not completed or Payment has already been fulfilled");
    }

    PaymentApprovedResponse response = paymentApis.requestPaymentApprove(paymentInfo);
    if (!paymentApis.isPaymentApproved(response.getStatus())) {
      return false;
    }

    TransactionTypeRepository transactionTypeRepository = findTransactionTypeRepositoryByPaymentMethod(
        PaymentMethod.from(response.getMethod()));
    transactionTypeRepository.save(response.toTransactionTypeEntity());
    paymentLedgerRepository.save(response.toPaymentLedgerEntity());
    order.paymentFulfill(response.getPaymentKey());

    return true;
  }

  private TransactionTypeRepository findTransactionTypeRepositoryByPaymentMethod(
      PaymentMethod paymentMethod) {
    switch (paymentMethod) {
      case CARD -> {
        return findTransactionTypeRepositoryByPrefix("card");
      }
      default ->
          throw new IllegalArgumentException("Unsupported payment method : " + paymentMethod);
    }
  }

  private TransactionTypeRepository findTransactionTypeRepositoryByPrefix(String prefix) {
    return transactionTypeRepositorySet.stream()
        .filter(r -> prefix.equalsIgnoreCase(
            r.getClass().getSimpleName().split("TransactionTypeRepository")[0]))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(("Unsupported prefix: " + prefix)));
  }
}
