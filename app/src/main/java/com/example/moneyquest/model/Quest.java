package com.example.moneyquest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Quest {

    private String title;
    private String description;
    private Long reward;

    public Quest() {
    }

    public Quest(String title, String description, Long reward) {
        this.title = title;
        this.description = description;
        this.reward = reward;
    }

    public static List<Quest> fromHashMap(List<HashMap<String, Object>> questMaps) {
        List<Quest> quests = new ArrayList<>();
        for (HashMap<String, Object> questMap : questMaps) {
            if(questMap != null) {
                Double rw = (Double) questMap.get("reward");
                Quest quest = new Quest();
                quest.setTitle((String) questMap.get("title"));
                quest.setDescription((String) questMap.get("description"));
                quest.setReward(rw.longValue());
                quests.add(quest);
            }
        }

        return quests;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }
}
