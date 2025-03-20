package com.mihir.MyFinace.services;

import com.mihir.MyFinace.models.Transaction;
import com.mihir.MyFinace.repository.ReportRepository;
import com.mihir.MyFinace.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private TransactionRepository transactionRepository;

    public FinancialSummary generateFinancialSummary(String userId) {
        List<Transaction> transactions = transactionRepository.findAll(); // Fetch transactions (modify if needed)

        double totalIncome = transactions.stream()
                .filter(t -> "income".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpenses = transactions.stream()
                .filter(t -> "expense".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        return new FinancialSummary(totalIncome, totalExpenses);
    }
}
