package com.playclip.test.transactions.command.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.playclip.test.transactions.command.TransactionCommand;
import com.playclip.test.transactions.dto.UserTotal;
import com.playclip.test.transactions.presenter.JsonPrinter;
import com.playclip.test.transactions.service.TransactionService;
import com.playclip.test.transactions.service.impl.TransactionServiceImpl;

import java.io.IOException;
import java.util.Map;

public class SumTransactionCommand implements TransactionCommand {

    TransactionService transactionService;
    JsonPrinter jsonPrinter;

    public SumTransactionCommand() {
        transactionService = new TransactionServiceImpl();
        jsonPrinter = new JsonPrinter();
    }

    @Override
    public void execute(Map<String, String> arguments) throws IOException {
        printSumJson(transactionService.sum(
                Long.valueOf(arguments.get("userId"))));
    }

    private void printSumJson(UserTotal total) throws JsonProcessingException {
        if(total == null)
            System.out.println("{}");
        else
            jsonPrinter.printTotalJson(total);
    }
}
