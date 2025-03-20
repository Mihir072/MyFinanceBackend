package com.mihir.MyFinace.controllers;

import com.mihir.MyFinace.models.Transaction;
import com.mihir.MyFinace.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable String id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable String id) {
        return transactionService.deleteTransaction(id);
    }
}
