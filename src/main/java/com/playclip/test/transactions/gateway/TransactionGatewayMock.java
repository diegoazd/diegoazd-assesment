package com.playclip.test.transactions.gateway;

import com.playclip.test.transactions.dto.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class TransactionGatewayMock implements TransactionGateway {

    Queue<Transaction> transactions;

    public TransactionGatewayMock() {
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
    public void add(Long userId, Transaction transaction) {
    }

    @Override
    public Transaction show(Long userId, UUID transactionId) {
        if(userId.equals(1l)) {
            buildTransactions();
          return transactions.peek();
        }else {
          return null;
        }
    }

    @Override
    public Queue<Transaction> list(Long userId) {
        if(userId.equals(1l)) {
            buildTransactions();
            return this.transactions;
        }else {
            return null;
        }
    }
}
