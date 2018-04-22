package com.playclip.test.transactions.factory;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.gateway.TransactionGateway;
import com.playclip.test.transactions.gateway.TransactionGatewayFileSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TransactionGatewayFileSystemTest {

    TransactionGateway transactionGateway;
    Long userId;

    @Before
    public void setup() {
        transactionGateway = new TransactionGatewayFileSystem();
        userId = new Random().nextLong();
    }

    @After
    public void teardown() {
        File file = new File(userId.toString());
        if(file.exists()) {
            file.delete();
        }

    }

    @Test
    public void shouldAddTransaction() throws IOException {
        Transaction transaction = new Transaction(userId,
                LocalDate.of(2015,12,21), BigDecimal.TEN,
                "description");
        transaction.setTransactionId(UUID.randomUUID());

        Transaction transaction2 = new Transaction(userId,
                LocalDate.of(2015,12,21), BigDecimal.ONE,
                "description2");
        transaction2.setTransactionId(UUID.randomUUID());
        transactionGateway.add(userId, transaction);
        transactionGateway.add(userId, transaction2);

        try (Stream<String> lines = Files.lines(Paths.get(userId.toString()))) {
            assertTrue(2l == lines.count());
        }
    }

    @Test
    public void shouldReturnNullWhenFileNotFound() throws IOException {
        assertNull(transactionGateway.show(userId, UUID.randomUUID()));
    }

    @Test
    public void shouldReturnNullWhenTransactionNotFound() throws IOException {
        Transaction transaction = new Transaction(userId,
                LocalDate.of(2015,12,21), BigDecimal.ONE,
                "description2");
        UUID uuid = UUID.randomUUID();
        transaction.setTransactionId(uuid);
        transactionGateway.add(userId, transaction);
        assertNull(transactionGateway.show(userId, UUID.randomUUID()));
    }

    @Test
    public void shouldReturnTransactionWhenFoundIt() throws IOException {
        UUID uuid = buildTransactions();

        Transaction response  = transactionGateway.show(userId, uuid);
        assertNotNull(response);
        assertEquals(uuid, response.getTransactionId());
        assertEquals(userId, response.getUserId());
        assertEquals(LocalDate.of(2015,12,21), response.getDate());
        assertEquals(BigDecimal.ONE, response.getAmount());
        assertEquals("description2", response.getDescription());
    }

    @Test
    public void shouldGetTransactionList() throws IOException {
        buildTransactions();
        Queue<Transaction> transactions = transactionGateway.list(userId);

        assertNotNull(transactions);
        assertTrue(transactions.size() == 2);
        Transaction transaction = transactions.remove();
        assertEquals(transaction.getDate(), LocalDate.of(2015,12,23));
        assertEquals(transaction.getAmount(), BigDecimal.TEN);
        assertEquals("description", transaction.getDescription());

        transaction = transactions.remove();
        assertEquals(transaction.getDate(), LocalDate.of(2015,12,21));
        assertEquals(transaction.getAmount(), BigDecimal.ONE);
        assertEquals("description2", transaction.getDescription());


        assertTrue(transactions.isEmpty());
    }

    @Test
    public void shouldReturnNullListWhenFileNotFound() throws IOException {
        assertNull(transactionGateway.list(userId));
    }

    private UUID buildTransactions() throws IOException {
        Transaction transaction = new Transaction(userId,
                LocalDate.of(2015,12,23), BigDecimal.TEN,
                "description");
        transaction.setTransactionId(UUID.randomUUID());

        Transaction transaction2 = new Transaction(userId,
                LocalDate.of(2015,12,21), BigDecimal.ONE,
                "description2");
        UUID uuid = UUID.randomUUID();
        transaction2.setTransactionId(uuid);
        transactionGateway.add(userId, transaction);
        transactionGateway.add(userId, transaction2);

        return uuid;
    }

}