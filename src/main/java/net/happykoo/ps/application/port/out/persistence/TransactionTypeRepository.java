package net.happykoo.ps.application.port.out.persistence;

import net.happykoo.ps.domain.payment.TransactionType;

public interface TransactionTypeRepository {

  TransactionType findById(String paymentKey);

  void save(TransactionType transactionType);
}
