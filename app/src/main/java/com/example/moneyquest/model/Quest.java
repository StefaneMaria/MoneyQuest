package com.example.moneyquest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Quest {

    private String title;
    private String description;
    private Double reward;
    private String image;

    public Quest() {
    }

    public Quest(String title, String description, Double reward) {
        this.title = title;
        this.description = description;
        this.reward = reward;
    }

    public static List<Quest> fromHashMap(List<HashMap<String, Object>> questMaps) {
        List<Quest> quests = new ArrayList<>();
        for (HashMap<String, Object> questMap : questMaps) {
            if(questMap != null) {
                Quest quest = new Quest();
                quest.setTitle((String) questMap.get("title"));
                quest.setDescription((String) questMap.get("description"));
                quest.setReward(quest.mapNumbers(questMap.get("reward")));
                quest.setImage((String) questMap.get("image"));
                quests.add(quest);
            }
        }

        return quests;
    }

    private Double mapNumbers (Object value) {
        if(value instanceof Long)
            return ((Long) value).doubleValue();
        return (Double) value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }
}
