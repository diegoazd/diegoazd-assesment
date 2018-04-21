package com.playclip.test.transactions.gateway;

import com.playclip.test.transactions.dto.Transaction;

import java.io.IOException;
import java.util.Queue;
import java.util.UUID;

public interface TransactionGateway {

    void add(Transaction transaction) throws IOException;
    Transaction show(Long userId, UUID transactionId) throws IOException;
    Queue<Transaction> list(Long userId) throws IOException;
}
