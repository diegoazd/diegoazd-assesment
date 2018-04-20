package com.playclip.test.transactions.config;

public class StorageState {
    private static StorageInterfaceType storageInterfaceType = StorageInterfaceType.FILE_SYSTEM;

    public static StorageInterfaceType getStorageInterfaceType() {
        return storageInterfaceType;
    }

    public static void setStorageInterfaceType(StorageInterfaceType storageInterfaceType) {
        StorageState.storageInterfaceType = storageInterfaceType;
    }
}
