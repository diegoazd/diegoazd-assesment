package com.playclip.test.transactions.service.impl;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.dto.UserTotal;
import com.playclip.test.transactions.factory.TransactionGatewayFactory;
import com.playclip.test.transactions.gateway.TransactionGateway;
import com.playclip.test.transactions.service.TransactionService;

import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    TransactionGateway transactionGateway = TransactionGatewayFactory.getGateway();

    @Override
    public Transaction add(Transaction transaction) {
        return transactionGateway.add(transaction);
    }

    @Override
    public Transaction show(Long userId, UUID transactionId) {
        return transactionGateway.show(userId, transactionId);
    }

    @Override
    public List<Transaction> list(Long userId) {
        return null;
    }

    @Override
    public UserTotal sum(Long userId) {
        return null;
    }
}
