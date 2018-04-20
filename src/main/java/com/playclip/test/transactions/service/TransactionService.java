package com.playclip.test.transactions.service;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.dto.UserTotal;

import java.util.Queue;
import java.util.UUID;

public interface TransactionService {

    Transaction add(Transaction transaction);
    Transaction show(Long userId, UUID transactionId);
    Queue<Transaction> list(Long userId);
    UserTotal sum(Long userId);

}
