package com.example.moneyquest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Safe {
    private String name;
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
                safe.setBalance(safe.mapNumbers(safeMap.get("balance")));
                safe.setGoal(safe.mapNumbers(safeMap.get("balance")));
                safe.setImage((String) safeMap.get("image"));
                safe.setName((String) safeMap.get("name"));
                safes.add(safe);
            }
        }

        return safes;
    }

    private Double mapNumbers (Object value) {
        if(value instanceof Long)
            return ((Long) value).doubleValue();
        return (Double) value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
