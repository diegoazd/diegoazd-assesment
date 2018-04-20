package com.playclip.test.transactions.factory;

import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.gateway.TransactionGateway;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
    public Transaction show(Long userId, UUID transactionId) throws IOException {
        File file = new File(userId.toString());
        if(file.exists()) {
            return getTransactionFromList(Files.readAllLines(Paths.get(userId.toString())),
                    transactionId.toString());
        }

        return null;
    }

    private Transaction getTransactionFromList(List<String> lines, String transactionId) {
        for(String t: lines) {
            String[] lineString = t.split(File.separator);
            if(lineString[0].equals(transactionId)) {
                return new Transaction(lineString);
            }
        }

        return null;
    }

    @Override
    public Queue<Transaction> list(Long userId) {
        return null;
    }
}
