package com.example.moneyquest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Safe {
    private String image;
    private Double goal;
    private Double balance;

    public Safe() {
    }

    public Safe(Double goal, Double balance) {
        this.image = "@drawable/pig";
        this.goal = goal;
        this.balance = balance;
    }

    public static List<Safe> fromHashMap(List<HashMap<String, Object>> safeMaps) {
        List<Safe> safes = new ArrayList<>();
        for (HashMap<String, Object> safeMap : safeMaps) {
            if(safeMap != null) {
                Safe safe = new Safe();
                safe.setBalance((Double) safeMap.get("balance"));
                safe.setGoal((Double) safeMap.get("goal"));
                safe.setImage((String) safeMap.get("image"));
                safes.add(safe);
            }
        }

        return safes;
    }

//    public static List<Safe> getSafesData() {
//        List<Safe> safes = new ArrayList<>();
//
//        // Create 3 Safe objects with mock data
//        safes.add(new Safe(1000, 500)); // Safe 1: Goal $1000, Balance $500 (image pre-set in constructor)
//        safes.add(new Safe(2500, 1234)); // Safe 2: Goal $2500, Balance $1234
//        safes.add(new Safe(750, 750)); // Safe 3: Goal $750, Balance $750 (fully funded)
//
//        return safes;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getGoal() {
        return goal;
    }

    public void setGoal(Double goal) {
        this.goal = goal;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
