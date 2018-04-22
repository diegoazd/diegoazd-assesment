package com.playclip.test.transactions.presenter;

import com.playclip.test.transactions.config.StorageInterfaceType;
import com.playclip.test.transactions.config.StorageState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TransactionInputTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        StorageState.setStorageInterfaceType(StorageInterfaceType.MOCK);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test(expected = RuntimeException.class)
    public void shouldCallAddService() throws IOException {
        String[] input = {"-DuserId=1","-Dcmd=add"};
        TransactionInput.main(input);
    }

    @Test
    public void shouldPrintTransactionForAddOperation() throws IOException {
        String[] input = {"-DuserId=1","-Dcmd=add", "-Duser_id=50",
            "-Ddescription=Joes tacos", "-Damount=150.00",
            "-Ddate=2018-01-25"};
        TransactionInput.main(input);
        String json = "{\"amount\":150.00,\"description\":\"Joes tacos\",\"date\":\"2018-01-25\",\"userId\":50,\"transactionId\":";
        assertEquals(json, outContent.toString().substring(0,92));
    }

    @Test(expected = IOException.class)
    public void shouldThrowsIOExceptionInAddoperation() throws IOException {
        String[] input = {"-DuserId=3","-Dcmd=add", "-Duser_id=50",
                "-Ddescription=Joes tacos", "-Damount=150.00",
                "-Ddate=2018-01-25"};
        TransactionInput.main(input);
    }

    @Test
    public void shouldNotFoundTransaction() throws IOException {
        String[] input = {"-DuserId=2","-Dcmd=2299ce24-9eaf-417f-82d6-e57f93777dc4"};
        TransactionInput.main(input);
        assertEquals("Transaction not found\n", outContent.toString());
    }

    @Test
    public void shouldPrintTransactionForShowOperation() throws IOException {
        String[] input = {"-DuserId=1","-Dcmd=2299ce24-9eaf-417f-82d6-e57f93777dc4"};
        TransactionInput.main(input);
        String json = "{\"amount\":150.0,\"description\":\"test description\",\"date\":\"2018-04-17\",\"userId\":1,\"transactionId\":";
        assertEquals(json, outContent.toString().substring(0,96));
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionForShow() throws IOException {
        String[] input = {"-DuserId=3","-Dcmd=2299ce24-9eaf-417f-82d6-e57f93777dc4"};
        TransactionInput.main(input);
    }

    @Test
    public void shouldPrintEmptyListWhenUserNotFound() throws IOException {
        String[] input = {"-DuserId=2","-Dcmd=list"};
        TransactionInput.main(input);
        assertEquals("[\n]\n", outContent.toString());
    }

    @Test
    public void shouldPrintTransactionList() throws IOException {
        String[] input = {"-DuserId=1","-Dcmd=list"};
        TransactionInput.main(input);
        assertNotEquals("[\n]\n", outContent.toString());
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionForList() throws IOException {
        String[] input = {"-DuserId=3","-Dcmd=list"};
        TransactionInput.main(input);
    }

    @Test
    public void shouldPrintEmptyTransactionSum() throws IOException {
        String[] input = {"-DuserId=2","-Dcmd=sum"};
        TransactionInput.main(input);
        assertEquals("{}\n", outContent.toString());
    }

    @Test
    public void shouldPrintUserSumOfTransactions() throws IOException {
        String[] input = {"-DuserId=1","-Dcmd=sum"};
        TransactionInput.main(input);
        assertEquals("{\"userId\":1,\"sum\":453.0}\n", outContent.toString());
    }

    @Test(expected = IOException.class)
    public void shouldThowExceptionForSum() throws IOException {
        String[] input = {"-DuserId=3","-Dcmd=sum"};
        TransactionInput.main(input);
    }
}