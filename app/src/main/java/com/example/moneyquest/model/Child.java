package com.example.moneyquest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kotlin.collections.EmptyList;

public class Child implements Serializable {
    private Double balance;
    private List<Quest> quests;
    private List<Safe> safes;

    public Child() {
        this.balance = 0.0;
        this.quests = new ArrayList<>();
        this.safes = new ArrayList<>();
    }

    public Child(Double balance, List<Quest> quests, List<Safe> safes) {
        this.balance = balance;
        this.quests = quests;
        this.safes = safes;
    }

    public static Child fromHashMap(HashMap<String, Object> map) {
        Child child = new Child();
        if (map.containsKey("balance") && mapNumbers(map.get("balance")) != 0) {
            child.setBalance((Double) map.get("balance"));
        }
        if (map.containsKey("quests")) {
            List<HashMap<String, Object>> questMaps = (List<HashMap<String, Object>>) map.get("quests");
            List<Quest> quests = Quest.fromHashMap(questMaps);
            child.setQuests(quests);
        }
        if (map.containsKey("safes")) {
            List<HashMap<String, Object>> safeMaps = (List<HashMap<String, Object>>) map.get("safes");
            List<Safe> safes = Safe.fromHashMap(safeMaps);
            child.setSafes(safes);
        }
        return child;
    }

    private static Double mapNumbers (Object value) {
        if(value instanceof Long)
            return ((Long) value).doubleValue();
        return (Double) value;
    }

    public void updateBalance(Double value) {
        this.balance += value;
    }

    public void removeQuestByTitle(String title) {
        Iterator<Quest> iterator = quests.iterator();
        while (iterator.hasNext()) {
            Quest quest = iterator.next();
            if (quest.getTitle().equals(title)) {
                iterator.remove();
                break;
            }
        }
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public List<Safe> getSafes() {
        return safes;
    }

    public void setSafes(List<Safe> safes) {
        this.safes = safes;
    }
}
