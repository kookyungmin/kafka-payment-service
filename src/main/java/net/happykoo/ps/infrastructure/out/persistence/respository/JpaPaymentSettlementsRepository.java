package net.happykoo.ps.infrastructure.out.persistence.respository;

import java.util.Optional;
import net.happykoo.ps.domain.settlements.PaymentSettlements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentSettlementsRepository extends JpaRepository<PaymentSettlements, Long> {

  Optional<PaymentSettlements> findByPaymentKey(String paymentKey);

}
