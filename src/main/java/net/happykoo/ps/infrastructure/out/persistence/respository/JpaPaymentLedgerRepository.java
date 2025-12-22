package net.happykoo.ps.infrastructure.out.persistence.respository;

import net.happykoo.ps.domain.payment.PaymentLedger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentLedgerRepository extends JpaRepository<PaymentLedger, Long> {

}
