package com.playclip.test.transactions.service.impl;

import com.playclip.test.transactions.config.StorageInterfaceType;
import com.playclip.test.transactions.config.StorageState;
import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.service.TransactionService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.*;

public class TransactionServiceImplTest {
    TransactionService transactionService;

    @Before
    public void setUp() {
        transactionService = new TransactionServiceImpl();
        StorageState.setStorageInterfaceType(StorageInterfaceType.MOCK);
    }

    @Test
    public void shouldAddTransaction() {
        Transaction transaction = new Transaction(1l, LocalDate.of(2018, 04,18),
                BigDecimal.valueOf(150.00d), "test description");
        Transaction response = transactionService.add(transaction);
        assertNotNull(response.getTransactionId());
        assertEquals(transaction.getAmount(), response.getAmount());
        assertEquals(transaction.getDate(), response.getDate());
        assertEquals(transaction.getDescription(), response.getDescription());
        assertEquals(transaction.getUserId(), response.getUserId());
    }

    @Test
    public void shouldShowTransaction() {
        Transaction transaction = transactionService.show(1l, UUID.randomUUID());
        assertNotNull(transaction);
    }

    @Test
    public void shouldNotFoundTransaction() {
        Transaction transaction = transactionService.show(2l, UUID.randomUUID());
        assertNull(transaction);
    }

}