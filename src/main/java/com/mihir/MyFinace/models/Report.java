package com.mihir.MyFinace.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reports")
public class Report {
    private double totalIncome;
    private double totalExpenses;
    private double netBalance;

    // ✅ Default constructor (needed for frameworks like Spring Data)
    public Report() {}

    // ✅ Add this constructor to fix the error
    public Report(double totalIncome, double totalExpenses, double netBalance) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.netBalance = netBalance;
    }

    // Getters and Setters
    public double getTotalIncome() { return totalIncome; }
    public void setTotalIncome(double totalIncome) { this.totalIncome = totalIncome; }

    public double getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(double totalExpenses) { this.totalExpenses = totalExpenses; }

    public double getNetBalance() { return netBalance; }
    public void setNetBalance(double netBalance) { this.netBalance = netBalance; }
}
