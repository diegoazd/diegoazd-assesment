package com.playclip.test.transactions.service;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.dto.UserTotal;

import java.io.IOException;
import java.util.Queue;
import java.util.UUID;

public interface TransactionService {

    Transaction add(Transaction transaction) throws IOException;
    Transaction show(Long userId, UUID transactionId) throws IOException;
    Queue<Transaction> list(Long userId) throws IOException;
    UserTotal sum(Long userId) throws IOException;

}
