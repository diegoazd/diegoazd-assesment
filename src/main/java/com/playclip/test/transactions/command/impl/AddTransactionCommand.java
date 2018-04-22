package com.playclip.test.transactions.command.impl;

import com.playclip.test.transactions.command.TransactionCommand;
import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.presenter.JsonPrinter;
import com.playclip.test.transactions.service.TransactionService;
import com.playclip.test.transactions.service.impl.TransactionServiceImpl;

import java.io.IOException;
import java.util.Map;

public class AddTransactionCommand implements TransactionCommand {

    TransactionService transactionService;
    JsonPrinter jsonPrinter;

    public AddTransactionCommand() {
        transactionService = new TransactionServiceImpl();
        jsonPrinter = new JsonPrinter();
    }

    @Override
    public void execute(Map<String, String> arguments) throws IOException {
        jsonPrinter.printTransactionJson(transactionService.add(
                Long.valueOf(arguments.get("userId")),
                new Transaction().getTransactionFromMap(arguments)));
    }
}
