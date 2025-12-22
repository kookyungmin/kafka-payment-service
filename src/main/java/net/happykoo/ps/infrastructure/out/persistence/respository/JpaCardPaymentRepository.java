package net.happykoo.ps.infrastructure.out.persistence.respository;

import net.happykoo.ps.domain.payment.card.CardPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCardPaymentRepository extends JpaRepository<CardPayment, String> {

}
