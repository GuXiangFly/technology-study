package cn.guxiangfly.domain;

import java.math.BigDecimal;

public class Account {

    private Long id;
    private Long accountNumber;
    private Long available;

    /**
     * 取款
     * @param money
     */
    public void withdraw(Long money) {
        available = available - money;
    }

    /**
     * 存款
     * @param money
     */
    public void deposit(Long money) {
        available = available + money;
    }
}
