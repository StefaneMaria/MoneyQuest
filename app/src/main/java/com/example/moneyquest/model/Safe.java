package com.example.moneyquest.model;

import java.util.ArrayList;
import java.util.List;

public class Safe {
    private String image;
    private int goal;
    private int balance;

    public Safe(int goal, int balance) {
        this.image = "@drawable/pig";
        this.goal = goal;
        this.balance = balance;
    }

    public static List<Safe> getSafesData() {
        List<Safe> safes = new ArrayList<>();

        // Create 3 Safe objects with mock data
        safes.add(new Safe(1000, 500)); // Safe 1: Goal $1000, Balance $500 (image pre-set in constructor)
        safes.add(new Safe(2500, 1234)); // Safe 2: Goal $2500, Balance $1234
        safes.add(new Safe(750, 750)); // Safe 3: Goal $750, Balance $750 (fully funded)

        return safes;
    }

    public String getImage() {
        return image;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
