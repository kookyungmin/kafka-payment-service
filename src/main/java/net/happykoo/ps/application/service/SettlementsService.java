package net.happykoo.ps.application.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.happykoo.ps.application.port.in.FetchPaymentSettlementUseCase;
import net.happykoo.ps.application.port.out.api.PaymentApis;
import net.happykoo.ps.application.port.out.persistence.PaymentLedgerRepository;
import net.happykoo.ps.application.port.out.persistence.PaymentSettlementsRepository;
import net.happykoo.ps.domain.settlements.PaymentSettlements;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentSettlementsResponse;
import net.happykoo.ps.representation.in.web.request.payment.PaymentSettlement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SettlementsService implements FetchPaymentSettlementUseCase {

  private final PaymentApis paymentApis;
  private final PaymentSettlementsRepository paymentSettlementsRepository;
  private final PaymentLedgerRepository paymentLedgerRepository;

  public SettlementsService(@Qualifier("mockPayment") PaymentApis paymentApis,
      PaymentSettlementsRepository paymentSettlementsRepository,
      PaymentLedgerRepository paymentLedgerRepository) {
    this.paymentApis = paymentApis;
    this.paymentSettlementsRepository = paymentSettlementsRepository;
    this.paymentLedgerRepository = paymentLedgerRepository;
  }

  @Override
  public void fetch() throws IOException {
    List<PaymentSettlements> settlements = paymentApis.requestPaymentSettlement(
            createPaymentSettlement())
        .stream()
        .map(PaymentSettlementsResponse::toEntity)
        .toList();

    paymentSettlementsRepository.bulkInsert(settlements);
    paymentLedgerRepository.bulkInsert(
        settlements
            .stream()
            .map(PaymentSettlements::toPaymentLedgerEntity).toList());

  }

  private PaymentSettlement createPaymentSettlement() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String startDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).minusDays(3).format(formatter);
    String endDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).minusDays(1).format(formatter);
    return PaymentSettlement.builder()
        .startDate(startDate)
        .endDate(endDate)
        .page(1)
        .size(5000)
        .build();
  }
}
