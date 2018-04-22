package com.playclip.test.transactions.presenter;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.playclip.test.transactions.command.impl.AddTransactionCommand;
import com.playclip.test.transactions.command.impl.ListTransactionCommand;
import com.playclip.test.transactions.command.impl.ShowTransactionCommand;
import com.playclip.test.transactions.command.impl.SumTransactionCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TransactionInput {

    @DynamicParameter(names = "-D", description = "Dynamic parameters")
    public Map<String, String> params = new HashMap<>();

    public static void main(String args[]) throws IOException {
        TransactionInput main = new TransactionInput();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);

        switch (main.params.get("cmd")) {
            case "add":
                new AddTransactionCommand().execute(main.params);
                break;
            case "list":
                new ListTransactionCommand().execute(main.params);
                break;
            case "sum":
                new SumTransactionCommand().execute(main.params);
                break;
            default:
                new ShowTransactionCommand().execute(main.params);
        }

    }
}