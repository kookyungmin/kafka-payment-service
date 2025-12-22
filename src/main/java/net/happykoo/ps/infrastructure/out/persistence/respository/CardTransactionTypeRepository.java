package net.happykoo.ps.infrastructure.out.persistence.respository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.out.persistence.TransactionTypeRepository;
import net.happykoo.ps.domain.payment.TransactionType;
import net.happykoo.ps.domain.payment.card.CardPayment;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CardTransactionTypeRepository implements TransactionTypeRepository {

  private final JpaCardPaymentRepository jpaCardPaymentRepository;

  @Override
  public TransactionType findById(String paymentKey) {
    return jpaCardPaymentRepository.findById(paymentKey)
        .orElseThrow(
            () -> new EntityNotFoundException("card payment does not exists : " + paymentKey));
  }

  @Override
  public void save(TransactionType transactionType) {
    jpaCardPaymentRepository.save((CardPayment) transactionType);
  }
}
