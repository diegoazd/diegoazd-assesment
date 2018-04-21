package com.playclip.test.transactions.presenter;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.playclip.test.transactions.command.impl.AddTransactionCommand;

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
        }

    }
}
