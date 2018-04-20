package com.playclip.test.transactions.factory;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.gateway.TransactionGateway;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Queue;
import java.util.UUID;

public class TransactionGatewayFileSystem implements TransactionGateway {

    @Override
    public Transaction add(Transaction transaction) throws IOException {
        File file = new File(transaction.getUserId().toString());
        FileUtils.writeStringToFile(file, transaction.toString()+"\n",
                StandardCharsets.UTF_8, true);

        return transaction;
    }

    @Override
    public Transaction show(Long userId, UUID transactionId) {
        return null;
    }

    @Override
    public Queue<Transaction> list(Long userId) {
        return null;
    }
}
