package net.happykoo.ps.infrastructure.out.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.ps.application.port.out.mq.Producer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer<T> implements Producer<T> {

  private final KafkaTemplate<String, T> kafkaTemplate;

  @Override
  public boolean send(String topic, T record) {
    log.info("Sending message to topic : {} / payload : {} ", topic, record.toString());
    kafkaTemplate.send(topic, record);
    return true;
  }

  @Override
  public boolean send(String topic, String key, T record) {
    return false;
  }
}
