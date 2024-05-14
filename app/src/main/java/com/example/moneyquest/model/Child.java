package com.example.moneyquest.model;

import java.util.List;

public class Child {
    private String nome;
    private String email;
    private String senha;
    private Long balance;
    private List<Quest> quests;
    private List<Safe> safes;

    public Child(String nome, String email, String senha, Long balance, List<Quest> quests, List<Safe> safes) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.balance = balance;
        this.quests = quests;
        this.safes = safes;
    }

    public Child(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
