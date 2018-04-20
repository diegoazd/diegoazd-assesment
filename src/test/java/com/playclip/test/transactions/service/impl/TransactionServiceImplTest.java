package com.playclip.test.transactions.service.impl;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.service.TransactionService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class TransactionServiceImplTest {
    TransactionService transactionService;

    @Before
    public void setUp() {
        transactionService = new TransactionServiceImpl();
    }

    @Test
    public void shouldAddTransaction() {
        Transaction transaction = new Transaction(1l, LocalDate.of(2018, 04,18),
                BigDecimal.valueOf(150.00d), "test description");
        Transaction response = transactionService.add(transaction);
        assertNotNull(transaction.getTransactionId());
    }

}