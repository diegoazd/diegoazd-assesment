package com.playclip.test.transactions.factory;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.gateway.TransactionGateway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
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
        transactionGateway.add(transaction);
        transactionGateway.add(transaction2);

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
        transactionGateway.add(transaction);
        assertNull(transactionGateway.show(userId, UUID.randomUUID()));
    }

    @Test
    public void shouldReturnTransactionWhenFoundIt() throws IOException {
        Transaction transaction = new Transaction(userId,
                LocalDate.of(2015,12,21), BigDecimal.TEN,
                "description");
        transaction.setTransactionId(UUID.randomUUID());

        Transaction transaction2 = new Transaction(userId,
                LocalDate.of(2015,12,23), BigDecimal.ONE,
                "description2");
        UUID uuid = UUID.randomUUID();
        transaction2.setTransactionId(uuid);
        transactionGateway.add(transaction);
        transactionGateway.add(transaction2);

        Transaction response  = transactionGateway.show(userId, uuid);
        assertNotNull(response);
        assertEquals(uuid, response.getTransactionId());
        assertEquals(userId, response.getUserId());
        assertEquals(LocalDate.of(2015,12,23), response.getDate());
        assertEquals(BigDecimal.ONE, response.getAmount());
        assertEquals("description2", response.getDescription());
    }
}