package com.team4.model;

import java.util.Date;

public class TransactionDetail {
    private int id;
    private double amount;
    private java.sql.Date date;
    private Transaction transaction;
    private Category category;
    private Wallet wallet;
    private String notes;

    public TransactionDetail() {
    }

    public TransactionDetail(double amount, java.sql.Date date, Transaction transaction, Category category, Wallet wallet, String notes) {
        this.amount = amount;
        this.date = date;
        this.transaction = transaction;
        this.category = category;
        this.wallet = wallet;
        this.notes = notes;
    }

    public TransactionDetail(int id, double amount, java.sql.Date date, Transaction transaction, Category category, Wallet wallet, String notes) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.transaction = transaction;
        this.category = category;
        this.wallet = wallet;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
