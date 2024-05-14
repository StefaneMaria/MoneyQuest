package com.example.moneyquest.model;

public class Quest {

    private String title;
    private String description;
    private Long reward;

    public Quest(String title, String description, Long reward) {
        this.title = title;
        this.description = description;
        this.reward = reward;
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
