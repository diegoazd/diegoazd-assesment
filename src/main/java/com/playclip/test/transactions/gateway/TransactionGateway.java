package com.playclip.test.transactions.gateway;

import com.playclip.test.transactions.dto.Transaction;

public interface TransactionGateway {

    Transaction add(Transaction transaction);

}
