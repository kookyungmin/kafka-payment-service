package net.happykoo.ps.infrastructure.out.persistence.respository;

import jakarta.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.out.persistence.PaymentSettlementsRepository;
import net.happykoo.ps.domain.settlements.PaymentSettlements;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentSettlementsRepositoryImpl implements PaymentSettlementsRepository {

  private final JpaPaymentSettlementsRepository jpaPaymentSettlementsRepository;
  private final JdbcTemplate jdbcTemplate;

  @Override
  public PaymentSettlements findById(String paymentKey) {
    return jpaPaymentSettlementsRepository.findByPaymentKey(paymentKey)
        .orElseThrow(() -> new EntityNotFoundException(
            "payment settlements does not exists : " + paymentKey));
  }

  @Override
  public void bulkInsert(List<PaymentSettlements> paymentSettlements) {
    String sql = """
        INSERT INTO payment_settlements (payment_id, method, settlements_status, total_amount, pay_out_amount, canceled_amount, sold_date, paid_out_date) 
        values (?, ?, ?, ?, ?, ?, ?, ?) 
        """;

    jdbcTemplate.batchUpdate(sql, paymentSettlements, paymentSettlements.size(), (ps, data) -> {
      String paymentMethodName = data.getMethod().getName();
      ps.setString(1, data.getPaymentKey());
      ps.setString(2, paymentMethodName);
      ps.setString(3, data.getPaymentStatus().toString());
      ps.setInt(4, data.getTotalAmount());
      ps.setInt(5, data.getPayOutAmount());
      ps.setInt(6, data.getCanceledAmount());
      ps.setDate(7, Date.valueOf(data.getSoldDate()));
      ps.setDate(8, Date.valueOf(data.getPaidOutDate()));
    });
  }
}
