package com.team4.model;

public class CurrencyWallet {
    int id;
    String name;

    public CurrencyWallet() {
    }

    public CurrencyWallet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CurrencyWallet(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
