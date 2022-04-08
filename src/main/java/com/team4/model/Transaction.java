package com.team4.model;

public class Transaction {
    private int id;
    private String name;

    public Transaction() {
    }

    public Transaction(String name) {
        this.name = name;
    }

    public Transaction(int id, String name) {
        this.id = id;
        this.name = name;
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
