package com.example.moneyquest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Safe {
    private String name;
    private String color;
    private Integer goal;
    private Integer balance;

    public Safe() {
    }

    public Safe(String color, Integer goal, Integer balance) {
        this.color = "@drawable/pig";
        this.goal = goal;
        this.balance = balance;
    }

    public static List<Safe> fromHashMap(List<HashMap<String, Object>> safeMaps) {
        List<Safe> safes = new ArrayList<>();
        for (HashMap<String, Object> safeMap : safeMaps) {
            if(safeMap != null) {
                Safe safe = new Safe();
                safe.setBalance(safe.mapNumbers(safeMap.get("balance")));
                safe.setGoal(safe.mapNumbers(safeMap.get("goal")));
                safe.setColor((String) safeMap.get("color"));
                safe.setName((String) safeMap.get("name"));
                safes.add(safe);
            }
        }

        return safes;
    }

    private Integer mapNumbers (Object value) {
        if(value instanceof Long)
            return ((Long) value).intValue();
        return (Integer) value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
