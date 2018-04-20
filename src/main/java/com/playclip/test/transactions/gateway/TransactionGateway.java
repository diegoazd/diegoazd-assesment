package com.playclip.test.transactions.gateway;

import com.playclip.test.transactions.dto.Transaction;

import java.util.List;
import java.util.Queue;
import java.util.UUID;

public interface TransactionGateway {

    Transaction add(Transaction transaction);
    Transaction show(Long userId, UUID transactionId);
    Queue<Transaction> list(Long userId);
}
