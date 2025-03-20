package com.mihir.MyFinace.models;

import com.mihir.MyFinace.services.FinancialSummary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private List<Transaction> transactions;
    private FinancialSummary reports;

    public User() {
        this.transactions = new ArrayList<>();  // ✅ Initialize list
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.transactions = new ArrayList<>();  // ✅ Ensure transactions is initialized
    }

    public List<Transaction> getTransactions() {
        if (transactions == null) {
            transactions = new ArrayList<>();  // ✅ Prevent NullPointerException
        }
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public FinancialSummary getReports() {
        return reports;
    }

    public void setReports(FinancialSummary reports) {
        this.reports = reports;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}