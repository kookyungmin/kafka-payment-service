package net.happykoo.ps.application.port.out.mq;

public interface Producer<T> {

  boolean send(String topic, T record);

  boolean send(String topic, String key, T record);

}
