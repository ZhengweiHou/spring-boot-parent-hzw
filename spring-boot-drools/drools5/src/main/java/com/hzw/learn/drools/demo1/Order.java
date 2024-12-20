package com.hzw.learn.drools.demo1;

/**
 * @ClassName Order
 * @Description TODO
 * @Author houzw
 * @Date 2024/11/18
 **/
public class Order {
    private double amount;
    private double discount;

    public Order(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "amount=" + amount +
                ", discount=" + discount +
                '}';
    }
}


