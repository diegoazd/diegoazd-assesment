package com.playclip.test.transactions.service.impl;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.dto.UserTotal;
import com.playclip.test.transactions.service.TransactionService;

import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService{

    @Override
    public Transaction add(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction show(Long userId, UUID transactionId) {
        return null;
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
