package com.playclip.test.transactions.factory;

import com.playclip.test.transactions.config.StorageState;
import com.playclip.test.transactions.gateway.TransactionGateway;
import com.playclip.test.transactions.gateway.TransactionGatewayFileSystem;
import com.playclip.test.transactions.gateway.TransactionGatewayMock;

import static com.playclip.test.transactions.config.StorageInterfaceType.MOCK;

public class TransactionGatewayFactory {

    public static TransactionGateway getGateway() {
       if(StorageState.getStorageInterfaceType() == MOCK)
           return new TransactionGatewayMock();
        else
          return new TransactionGatewayFileSystem();
    }
}
