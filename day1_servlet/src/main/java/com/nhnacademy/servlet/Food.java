package com.nhnacademy.servlet;

public class Food {
    private final String name;
    private int cnt;
    private int amount;

    public Food(String name, int count, int amount) {
        this.name = name;
        this.cnt = count;
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
    public int getCnt() {
        return cnt;
    }

    @Override
    public String toString() {
        return "Food{" +
            "name='" + name + '\'' +
            ", cnt=" + cnt +
            ", amount=" + amount +
            '}';
    }
}
