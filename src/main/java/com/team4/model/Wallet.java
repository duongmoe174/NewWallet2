package com.team4.model;

import com.team4.config.SingletonConnection;

import java.sql.Connection;

public class Wallet {
    private int id;
    private String name;
    private double balance;
    private String description;
    private User user;
    private CurrencyWallet currencyWallet;

    public Wallet() {
    }

    public Wallet(String name, double balance, String description, User user, CurrencyWallet currencyWallet) {
        this.name = name;
        this.balance = balance;
        this.description = description;
        this.user = user;
        this.currencyWallet = currencyWallet;
    }

    public Wallet(String name, double balance, User user, CurrencyWallet currencyWallet) {
        this.name = name;
        this.balance = balance;
        this.user = user;
        this.currencyWallet = currencyWallet;
    }

    public Wallet(int id, String name, double balance, String description, User user, CurrencyWallet currencyWallet) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.description = description;
        this.user = user;
        this.currencyWallet = currencyWallet;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CurrencyWallet getCurrencyWallet() {
        return currencyWallet;
    }

    public void setCurrencyWallet(CurrencyWallet currencyWallet) {
        this.currencyWallet = currencyWallet;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", currencyWallet=" + currencyWallet +
                '}';
    }
}
