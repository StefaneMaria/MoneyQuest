package com.example.moneyquest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kotlin.collections.EmptyList;

public class Child {
    private Long balance;
    private List<Quest> quests;
    private List<Safe> safes;

    public Child() {
        this.balance = 0L;
        this.quests = new ArrayList<>();
        this.safes = new ArrayList<>();
    }

    public Child(Long balance, List<Quest> quests, List<Safe> safes) {
        this.balance = balance;
        this.quests = quests;
        this.safes = safes;
    }

    public static Child fromHashMap(HashMap<String, Object> map) {
        Child child = new Child();
        if (map.containsKey("balance")) {
            child.setBalance((Long) map.get("balance"));
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
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
