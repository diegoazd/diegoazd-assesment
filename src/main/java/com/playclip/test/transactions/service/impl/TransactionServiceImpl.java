package com.playclip.test.transactions.service.impl;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.dto.UserTotal;
import com.playclip.test.transactions.factory.TransactionGatewayFactory;
import com.playclip.test.transactions.gateway.TransactionGateway;
import com.playclip.test.transactions.service.TransactionService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Queue;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    TransactionGateway transactionGateway;

    public TransactionServiceImpl() {
        transactionGateway = TransactionGatewayFactory.getGateway();
    }

    @Override
    public Transaction add(Transaction transaction) throws IOException {
        transaction.setTransactionId(UUID.randomUUID());
        return transactionGateway.add(transaction);
    }

    @Override
    public Transaction show(Long userId, UUID transactionId) {
        return transactionGateway.show(userId, transactionId);
    }

    @Override
    public Queue<Transaction> list(Long userId) {
        return transactionGateway.list(userId);
    }

    @Override
    public UserTotal sum(Long userId) {
        Queue<Transaction> transactions = transactionGateway.list(userId);

        if(transactions == null) {
            return new UserTotal(userId, BigDecimal.ZERO);
        }
        return getUserTotal(transactions, userId);
    }

    private UserTotal getUserTotal(Queue<Transaction> transactions, Long userId) {
        BigDecimal total = BigDecimal.ZERO;
        while(!transactions.isEmpty()) {
            Transaction t = transactions.remove();
            total = total.add(t.getAmount());
        }

        return new UserTotal(userId, total);
    }
}
