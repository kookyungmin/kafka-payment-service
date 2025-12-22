package net.happykoo.ps.infrastructure.out.persistence.respository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.out.persistence.PaymentLedgerRepository;
import net.happykoo.ps.domain.payment.PaymentLedger;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentLedgerRepositoryImpl implements PaymentLedgerRepository {

  private final JpaPaymentLedgerRepository jpaPaymentLedgerRepository;

  @Override
  public List<PaymentLedger> findAllByPaymentKey(String paymentKey) {
    return null;
  }

  @Override
  public PaymentLedger findOneByPaymentKeyDesc(String paymentKey) {
    return null;
  }

  @Override
  public void save(PaymentLedger paymentLedger) {
    jpaPaymentLedgerRepository.save(paymentLedger);
  }
}
