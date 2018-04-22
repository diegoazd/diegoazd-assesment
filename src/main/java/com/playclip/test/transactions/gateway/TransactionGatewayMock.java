package com.playclip.test.transactions.gateway;

import com.playclip.test.transactions.dto.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class TransactionGatewayMock implements TransactionGateway {

    Queue<Transaction> transactions;

    public TransactionGatewayMock() {
        buildTransactions();
    }

    private void buildTransactions() {
        transactions = new PriorityQueue<>();
        Transaction transaction = new Transaction(1l, LocalDate.of(2018, 04,17),
                BigDecimal.valueOf(150.00d), "test description");
        transaction.setTransactionId(UUID.randomUUID());
        Transaction transaction2 = new Transaction(1l, LocalDate.of(2018, 04,16),
                BigDecimal.valueOf(151.00d), "test description2");
        transaction.setTransactionId(UUID.randomUUID());
        Transaction transaction3 = new Transaction(1l, LocalDate.of(2018, 04,16),
                BigDecimal.valueOf(152.00d), "test description3");
        transaction.setTransactionId(UUID.randomUUID());
        transactions.add(transaction);
        transactions.add(transaction2);
        transactions.add(transaction3);
    }

    @Override
    public void add(Long userId, Transaction transaction) throws IOException {
        if(userId.equals(3l)) {
            throw new IOException();
        }
    }

    @Override
    public Transaction show(Long userId, UUID transactionId) throws IOException {
        if(userId.equals(1l)) {
            return transactions.peek();
        }else if(userId.equals(3l)) {
            throw new IOException();
        }else {
          return null;
        }
    }

    @Override
    public Queue<Transaction> list(Long userId) throws IOException {
        if(userId.equals(1l)) {
            return this.transactions;
        }else if(userId.equals(3l)) {
            throw  new IOException();
        }else {
            return null;
        }
    }
}
