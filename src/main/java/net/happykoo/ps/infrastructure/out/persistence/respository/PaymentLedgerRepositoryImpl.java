package net.happykoo.ps.infrastructure.out.persistence.respository;

import jakarta.persistence.EntityNotFoundException;
import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.out.persistence.PaymentLedgerRepository;
import net.happykoo.ps.domain.payment.PaymentLedger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentLedgerRepositoryImpl implements PaymentLedgerRepository {

  private final JpaPaymentLedgerRepository jpaPaymentLedgerRepository;
  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<PaymentLedger> findAllByPaymentKey(String paymentKey) {
    return null;
  }

  @Override
  public PaymentLedger findOneByPaymentKeyDesc(String paymentKey) {
    return jpaPaymentLedgerRepository.findTopByPaymentKeyOrderByIdDesc(paymentKey)
        .orElseThrow(
            () -> new EntityNotFoundException("payment ledger does not exists : " + paymentKey));
  }

  @Override
  public void save(PaymentLedger paymentLedger) {
    jpaPaymentLedgerRepository.save(paymentLedger);
  }

  @Override
  public void bulkInsert(List<PaymentLedger> paymentLedgers) {
    String sqlStatement =
        "INSERT INTO payment_transaction (payment_id, method, payment_Status, total_amount, balance_amount, canceled_amount, pay_out_amount) "
            +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    jdbcTemplate.batchUpdate(
        sqlStatement,
        paymentLedgers,
        paymentLedgers.size(),
        (PreparedStatement ps, PaymentLedger data) -> {
          ps.setString(1, data.getPaymentKey());
          ps.setString(2, data.getMethod().getName());
          ps.setString(3, data.getPaymentStatus().toString());
          ps.setInt(4, data.getTotalAmount());
          ps.setInt(5, data.getBalanceAmount());
          ps.setInt(6, data.getCanceledAmount());
          ps.setInt(7, data.getPayOutAmount());
        });

  }
}
