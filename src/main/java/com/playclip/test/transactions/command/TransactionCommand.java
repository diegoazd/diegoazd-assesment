package com.playclip.test.transactions.command;

import java.io.IOException;
import java.util.Map;

public interface TransactionCommand {

    void execute(Map<String, String> arguments) throws IOException;
}
