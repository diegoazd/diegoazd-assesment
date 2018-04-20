package com.playclip.test.transactions.factory;

import com.playclip.test.transactions.config.StorageState;
import com.playclip.test.transactions.gateway.TransactionGateway;
import com.playclip.test.transactions.gateway.TransactionGatewayMock;

public class TransactionGatewayFactory {

    public static TransactionGateway getGateway() {
       switch (StorageState.getStorageInterfaceType()) {
            case MOCK:
                return new TransactionGatewayMock();
           case FILE_SYSTEM:
               return null;
        }

        throw new RuntimeException("Invalid option");
    }
}
