package com.playclip.test.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction implements Comparable {
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
    public int compareTo(Object o) {
        LocalDate d = ((Transaction)o).getDate();
        if(this.date.isAfter(d)) {
            return 1;
        }else {
            return 0;
        }
    }
}
