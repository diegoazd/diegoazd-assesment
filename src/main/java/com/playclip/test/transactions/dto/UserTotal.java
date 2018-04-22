package com.playclip.test.transactions.dto;

import java.math.BigDecimal;

public class UserTotal {
    private Long userId;
    private BigDecimal  sum;

    public UserTotal(Long userId, BigDecimal sum) {
        this.userId = userId;
        this.sum = sum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
