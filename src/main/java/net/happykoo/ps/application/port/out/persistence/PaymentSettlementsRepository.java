package net.happykoo.ps.application.port.out.persistence;

import java.util.List;
import net.happykoo.ps.domain.settlements.PaymentSettlements;

public interface PaymentSettlementsRepository {

  PaymentSettlements findById(String paymentKey);

  void bulkInsert(List<PaymentSettlements> paymentSettlements);
}
