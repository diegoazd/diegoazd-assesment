package com.playclip.test.transactions.factory;

import com.playclip.test.transactions.config.StorageInterfaceType;
import com.playclip.test.transactions.config.StorageState;
import com.playclip.test.transactions.gateway.TransactionGateway;
import com.playclip.test.transactions.gateway.TransactionGatewayMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionGatewayFactoryTest {

    @Test
    public void shouldGetMockInterface() {
        StorageState.setStorageInterfaceType(StorageInterfaceType.MOCK);
        assertTrue(TransactionGatewayFactory.getGateway() instanceof TransactionGatewayMock);
    }

    @Test
    public void shouldGetFileSystemInterface() {
        StorageState.setStorageInterfaceType(StorageInterfaceType.FILE_SYSTEM);
        assertTrue(TransactionGatewayFactory.getGateway() == null);
    }
}