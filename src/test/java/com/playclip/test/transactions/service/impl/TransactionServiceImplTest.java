package com.playclip.test.transactions.service.impl;

import com.playclip.test.transactions.config.StorageInterfaceType;
import com.playclip.test.transactions.config.StorageState;
import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.dto.UserTotal;
import com.playclip.test.transactions.service.TransactionService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Queue;
import java.util.UUID;

import static org.junit.Assert.*;

public class TransactionServiceImplTest {
    TransactionService transactionService;

    @Before
    public void setUp() {
        StorageState.setStorageInterfaceType(StorageInterfaceType.MOCK);
        transactionService = new TransactionServiceImpl();
    }

    @Test
    public void shouldAddTransaction() throws IOException {
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
    public void shouldShowTransaction() throws IOException {
        Transaction transaction = transactionService.show(1l, UUID.randomUUID());
        assertNotNull(transaction);
    }

    @Test
    public void shouldNotFoundTransaction() throws IOException {
        Transaction transaction = transactionService.show(2l, UUID.randomUUID());
        assertNull(transaction);
    }

    @Test
    public void shouldListTransactions() throws IOException {
        Queue<Transaction> transactions = transactionService.list(1l);
        assertNotNull(transactions);
        Transaction transaction = transactions.remove();
        assertEquals(LocalDate.of(2018,04,16),
                transaction.getDate());
        assertEquals(BigDecimal.valueOf(151.00d), transaction.getAmount());
        assertEquals("test description2", transaction.getDescription());

        transaction = transactions.remove();
        assertEquals(LocalDate.of(2018,04,16),
                transaction.getDate());
        assertEquals(BigDecimal.valueOf(152.00d), transaction.getAmount());
        assertEquals("test description3", transaction.getDescription());

        transaction = transactions.remove();
        assertEquals(LocalDate.of(2018,04,17),
                transaction.getDate());
        assertEquals(BigDecimal.valueOf(150.00d), transaction.getAmount());
        assertEquals("test description", transaction.getDescription());
    }

    @Test
    public void shouldReturnNull() throws IOException {
        Queue<Transaction> transactions = transactionService.list(2l);
        assertNull(transactions);
    }

    @Test
    public void shouldGetTotalFromTransactions() throws IOException {
        UserTotal userTotal = transactionService.sum(1l);
        assertNotNull(userTotal);
        assertEquals(userTotal.getUserId(), Long.valueOf(1l));
        assertEquals(userTotal.getSum(), BigDecimal.valueOf(453.00d));
    }

    @Test
    public void shouldGetTotalZeroWhenUserNotFound() throws IOException {
        UserTotal userTotal = transactionService.sum(2l);
        assertNotNull(userTotal);
        assertEquals(userTotal.getUserId(), Long.valueOf(2l));
        assertEquals(userTotal.getSum(), BigDecimal.ZERO);
    }

}