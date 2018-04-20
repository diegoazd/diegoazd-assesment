package com.playclip.test.transactions.dto;

import java.math.BigDecimal;

public class UserTotal {
    private Long userId;
    private BigDecimal  sum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
