package net.happykoo.ps.application.port.out.persistence;

import java.util.List;
import net.happykoo.ps.domain.payment.PaymentLedger;

public interface PaymentLedgerRepository {

  List<PaymentLedger> findAllByPaymentKey(String paymentKey);

  PaymentLedger findOneByPaymentKeyDesc(String paymentKey);

  void save(PaymentLedger paymentLedger);
}
