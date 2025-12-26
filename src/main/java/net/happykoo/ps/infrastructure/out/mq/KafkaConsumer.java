package net.happykoo.ps.infrastructure.out.mq;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.ps.application.port.out.persistence.PaymentLedgerRepository;
import net.happykoo.ps.application.port.out.persistence.PaymentSettlementsRepository;
import net.happykoo.ps.domain.payment.PaymentMethod;
import net.happykoo.ps.domain.payment.PaymentStatus;
import net.happykoo.ps.domain.settlements.PaymentSettlements;
import net.happykoo.ps.infrastructure.out.mq.record.RPaymentSettlements;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

  private final static String SETTLEMENTS_TOPIC = "settlements";

  private final PaymentSettlementsRepository paymentSettlementsRepository;
  private final PaymentLedgerRepository paymentLedgerRepository;

  @KafkaListener(topics = SETTLEMENTS_TOPIC)
  public void receive(ConsumerRecord<String, RPaymentSettlements> consumerRecord) {
    RPaymentSettlements payload = consumerRecord.value();
    log.info("received payload = {}", payload.getSettlements().get(0).getPaymentKey());
    List<PaymentSettlements> paymentSettlements = payload.getSettlements().stream()
        .map(record -> PaymentSettlements.builder()
            .paymentKey(record.getPaymentKey())
            .method(PaymentMethod.valueOf(record.getMethod()))
            .paymentStatus(PaymentStatus.valueOf("SETTLEMENTS_REQUESTED"))
            .totalAmount(record.getTotalAmount())
            .payOutAmount(record.getPayOutAmount())
            .canceledAmount(record.getCanceledAmount())
            .soldDate(LocalDate.ofEpochDay(record.getSoldDate()))
            .paidOutDate(LocalDate.ofEpochDay(record.getPaidOutDate()))
            .build()
        )
        .toList();

    paymentSettlementsRepository.bulkInsert(paymentSettlements);
    paymentLedgerRepository.bulkInsert(
        paymentSettlements
            .stream()
            .map(PaymentSettlements::toPaymentLedgerEntity).toList());
  }

}
