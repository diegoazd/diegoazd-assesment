package com.playclip.test.transactions.dto;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;

public class Transaction implements Comparable<Transaction> {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Long userId;
    private UUID transactionId;

    public Transaction(Long userId, LocalDate date, BigDecimal amount, String description) {
        this.userId = userId;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Transaction(String[] lineTransaction) {
      this.transactionId = UUID.fromString(lineTransaction[0]);
      this.amount = new BigDecimal(lineTransaction[1]);
      this.description = lineTransaction[2];
      this.date = LocalDate.parse(lineTransaction[3]);
      this.userId = Long.valueOf(lineTransaction[4]);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }


    @Override
    public int compareTo(Transaction o) {
        if(this.date.isAfter(o.date)) {
            return 1;
        }else if(this.date.isBefore(o.date)){
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return transactionId.toString()+File.separator+amount.toString()+File.separator+
                description+File.separator+date.toString()+File.separator+userId.toString();
    }
}
