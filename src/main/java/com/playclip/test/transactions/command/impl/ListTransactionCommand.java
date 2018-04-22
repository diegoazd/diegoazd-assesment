package com.playclip.test.transactions.command.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.playclip.test.transactions.command.TransactionCommand;
import com.playclip.test.transactions.dto.Transaction;
import com.playclip.test.transactions.presenter.JsonPrinter;
import com.playclip.test.transactions.service.TransactionService;
import com.playclip.test.transactions.service.impl.TransactionServiceImpl;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;

public class ListTransactionCommand implements TransactionCommand {

    TransactionService transactionService;
    JsonPrinter jsonPrinter;

    public ListTransactionCommand() {
        transactionService = new TransactionServiceImpl();
        jsonPrinter = new JsonPrinter();
    }

    @Override
    public void execute(Map<String, String> arguments) throws IOException {
        System.out.println("[");
        printTranasctions(transactionService.list(Long.valueOf(arguments.get("userId"))));
        System.out.println("]");
    }

    private void printTranasctions(Queue<Transaction> transactions) throws JsonProcessingException {
        while (transactions != null && !transactions.isEmpty()) {
            Transaction t = transactions.remove();
            jsonPrinter.printTransactionJson(t);
        }
    }
}
