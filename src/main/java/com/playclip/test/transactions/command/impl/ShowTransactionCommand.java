package com.playclip.test.transactions.command.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.playclip.test.transactions.command.TransactionCommand;
import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.presenter.JsonPrinter;
import com.playclip.test.transactions.service.TransactionService;
import com.playclip.test.transactions.service.impl.TransactionServiceImpl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class ShowTransactionCommand implements TransactionCommand {
    TransactionService transactionService;
    JsonPrinter jsonPrinter;

    public ShowTransactionCommand() {
        transactionService = new TransactionServiceImpl();
        jsonPrinter = new JsonPrinter();
    }

    @Override
    public void execute(Map<String, String> arguments) throws IOException {
        final Long userId = Long.valueOf(arguments.get("userId"));
        final UUID uuid = UUID.fromString(arguments.get("cmd"));
        printResponse(transactionService.show(userId, uuid));
    }

    private void printResponse(Transaction transaction) throws JsonProcessingException {
        if(transaction == null) {
            System.out.println("Transaction not found");
        }else {
            jsonPrinter.printTransactionJson(transaction);
        }
    }
}
