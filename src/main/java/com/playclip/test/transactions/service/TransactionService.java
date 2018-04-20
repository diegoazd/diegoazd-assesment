package com.playclip.test.transactions.service;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.dto.UserTotal;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction add(Transaction transaction);
    Transaction show(Long userId, UUID transactionId);
    List<Transaction> list(Long userId);
    UserTotal sum(Long userId);

}
