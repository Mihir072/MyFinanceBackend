package com.mihir.MyFinace.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reports")
public class Report {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(double netBalance) {
        this.netBalance = netBalance;
    }

    @Id
    private String id;
    private double totalIncome;
    private double totalExpenses;
    private double netBalance;
    // Getters and Setters
}

