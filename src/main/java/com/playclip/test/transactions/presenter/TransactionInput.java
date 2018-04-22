package com.playclip.test.transactions.presenter;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.playclip.test.transactions.command.TransactionCommand;
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

        final String cmd = main.params.get("cmd");
        switch (cmd) {
            case "add":
                execute(new AddTransactionCommand(), main.params);
                break;
            case "list":
                execute(new ListTransactionCommand(), main.params);
                break;
            case "sum":
                execute(new SumTransactionCommand(), main.params);
                break;
            default:
                execute(new ShowTransactionCommand(), main.params);
        }
    }

    private static void execute(TransactionCommand command, Map<String, String> params) throws IOException {
        command.execute(params);
    }
}
