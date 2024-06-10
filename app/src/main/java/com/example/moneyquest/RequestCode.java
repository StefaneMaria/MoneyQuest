package com.example.moneyquest;

public enum RequestCode {
    REQUEST_QUEST(001),
    REQUEST_SAFE(011),
    REQUEST_QUEST_SCREEN(201),
    REQUEST_SAFE_SCREEN(211),
    QUEST_SEND_CODE(10),
    SAFE_DEPOSIT_CODE(10),
    QUEST_SCREEN_CODE(20),
    SAFE_SCREEN_CODE(22),
    GOTO_SAFES(30),
    GOTO_QUESTS(33);


    private final int code;

    RequestCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}