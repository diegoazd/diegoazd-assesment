package com.playclip.test.transactions.dto;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TransactionTest {
    Transaction transaction;
    Map<String, String> map;

    @Before
    public void setup() {
        transaction = new Transaction();
        map = new HashMap<>();
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenAmountIsNotPresent() {
       transaction.getTransactionFromMap(map);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenUserIdIsNotPresent() {
        map.put("amount", "120.00");
        transaction.getTransactionFromMap(map);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenDescriptionsNotPresent() {
        map.put("amount", "120.00");
        map.put("user_id", "1");
        transaction.getTransactionFromMap(map);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenDateNotPresent() {
        map.put("amount", "120.00");
        map.put("user_id", "1");
        map.put("description", "Joes tacos");
        transaction.getTransactionFromMap(map);
    }

    @Test
    public void shouldReturnTransaction() {
        map.put("amount", "120.00");
        map.put("user_id", "1");
        map.put("description", "Joes tacos");
        map.put("date", "2018-12-30");
        Transaction t = transaction.getTransactionFromMap(map);
        assertNotNull(t);
        assertEquals(new BigDecimal("120.00"), t.getAmount());
        assertEquals(Long.valueOf(1l), t.getUserId());
        assertEquals("Joes tacos", t.getDescription());
        assertEquals(LocalDate.of(2018,12,30), t.getDate());
    }

}