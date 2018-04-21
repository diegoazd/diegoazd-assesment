package com.playclip.test.transactions.command.impl;

import com.playclip.test.transactions.command.TransactionCommand;
import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.service.TransactionService;
import com.playclip.test.transactions.service.impl.TransactionServiceImpl;

import java.io.IOException;
import java.util.Map;

public class AddTransactionCommand implements TransactionCommand {

    TransactionService transactionService;

    public AddTransactionCommand() {
        transactionService = new TransactionServiceImpl();
    }

    @Override
    public void execute(Map<String, String> arguments) throws IOException {
        transactionService.add(new Transaction().getTransactionFromMap(arguments));
    }
}
