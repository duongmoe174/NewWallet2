package com.team4.model;

import com.team4.config.SingletonConnection;

import java.sql.Connection;

public class Wallet {
    private int id_wallet;
    private String name_wallet;
    private String description;

    public Wallet() {
    }

    public Wallet(int id_wallet, String name_wallet, String description) {
        this.id_wallet = id_wallet;
        this.name_wallet = name_wallet;
        this.description = description;
    }

    public Wallet(String name_wallet, String description) {
        this.name_wallet = name_wallet;
        this.description = description;
    }

    public int getId_wallet() {
        return id_wallet;
    }

    public void setId_wallet(int id_wallet) {
        this.id_wallet = id_wallet;
    }

    public String getName_wallet() {
        return name_wallet;
    }

    public void setName_wallet(String name_wallet) {
        this.name_wallet = name_wallet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
