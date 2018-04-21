package com.playclip.test.transactions.presenter;

import com.playclip.test.transactions.config.StorageInterfaceType;
import com.playclip.test.transactions.config.StorageState;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TransactionInputTest {
    String json;

    @Before
    public void setup() {
        StorageState.setStorageInterfaceType(StorageInterfaceType.MOCK);
    }

    @Test(expected = RuntimeException.class)
    public void shouldCallAddService() throws IOException {
        String[] input = {"-DuserId=1","-Dcmd=add"};
        TransactionInput.main(input);
    }
}