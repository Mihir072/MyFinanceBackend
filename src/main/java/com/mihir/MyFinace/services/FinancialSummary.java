package com.mihir.MyFinace.services;

public class FinancialSummary {
    private double totalIncome;
    private double totalExpenses;
    private double netBalance;

    public FinancialSummary(double totalIncome, double totalExpenses) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.netBalance = totalIncome - totalExpenses;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getNetBalance() {
        return netBalance;
    }
}
