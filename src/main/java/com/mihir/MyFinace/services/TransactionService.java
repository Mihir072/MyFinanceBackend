package com.mihir.MyFinace.services;

import com.mihir.MyFinace.models.Transaction;
import com.mihir.MyFinace.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    public Transaction updateTransaction(String id, Transaction transaction) {
        transaction.setId(id);
        return transactionRepository.save(transaction);
    }
    public String deleteTransaction(String id) {
        transactionRepository.deleteById(id);
        return "Transaction deleted";
    }
}
