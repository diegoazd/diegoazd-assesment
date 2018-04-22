package com.playclip.test.transactions.presenter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.playclip.test.transactions.dto.Transaction;

public class JsonPrinter {
    ObjectMapper mapper;

    public JsonPrinter() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void printTransactionJson(Transaction transaction) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString(transaction));
    }
}
