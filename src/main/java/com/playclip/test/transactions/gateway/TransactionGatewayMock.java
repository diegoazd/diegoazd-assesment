package com.playclip.test.transactions.gateway;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.dto.UserTotal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class TransactionGatewayMock implements TransactionGateway {

    Queue<Transaction> transactions;

    public TransactionGatewayMock() {
        transactions = new PriorityQueue<>();
        Transaction transaction = new Transaction(1l, LocalDate.of(2018, 04,17),
                BigDecimal.valueOf(150.00d), "test description");
        transaction.setTransactionId(UUID.randomUUID());
        Transaction transaction2 = new Transaction(1l, LocalDate.of(2018, 04,16),
                BigDecimal.valueOf(150.00d), "test description");
        transaction.setTransactionId(UUID.randomUUID());
        transactions.add(transaction);
        transactions.add(transaction2);
    }

    @Override
    public Transaction add(Transaction transaction) {
        transaction.setTransactionId(UUID.randomUUID());
        return transaction;
    }

    @Override
    public Transaction show(Long userId, UUID transactionId) {
        System.out.println("Entraaa:"+userId);
        if(userId == 1l) {
          return transactions.peek();
        }else {
          return null;
        }
    }

    @Override
    public List<Transaction> list(Long userId) {
        return null;
    }
}
