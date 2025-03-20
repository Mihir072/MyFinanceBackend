package com.mihir.MyFinace.services;

import com.mihir.MyFinace.models.Transaction;
import com.mihir.MyFinace.models.User;
import com.mihir.MyFinace.repository.TransactionRepository;
import com.mihir.MyFinace.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encrypt Password
        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null); // ✅ Extract User object

        return user != null && passwordEncoder.matches(password, user.getPassword());
    }


    public User getUserByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> getUserById(String id){
        return userRepository.findById(id);
    }

    public User addTransaction(String username, Transaction transaction) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getTransactions() == null) {
            user.setTransactions(new ArrayList<>());
        }

        // ✅ Save transaction to generate `id`
        Transaction savedTransaction = transactionRepository.save(transaction);

        // ✅ Add transaction to user's list
        user.getTransactions().add(savedTransaction);

        // ✅ Update Reports after adding a transaction
        updateReports(user);

        return userRepository.save(user);
    }


    private void updateReports(User user) {
        double totalIncome = 0.0;
        double totalExpenses = 0.0;

        for (Transaction txn : user.getTransactions()) {
            if ("income".equalsIgnoreCase(txn.getType())) {
                totalIncome += txn.getAmount();
            } else if ("expense".equalsIgnoreCase(txn.getType())) {
                totalExpenses += txn.getAmount();
            }
        }

        double netBalance = totalIncome - totalExpenses;

        // ✅ Update user reports
        user.setReports(new FinancialSummary(totalIncome, totalExpenses, netBalance));
    }





    public User deleteTransaction(String userId, String txnId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.getTransactions().removeIf(t -> t.getTxnId().equals(txnId));
            updateFinancialSummary(user);
            return userRepository.save(user);
        }
        return null;
    }

    private void updateFinancialSummary(User user) {
        double totalIncome = user.getTransactions().stream()
                .filter(t -> t.getType().equalsIgnoreCase("income"))
                .mapToDouble(Transaction::getAmount).sum();

        double totalExpenses = user.getTransactions().stream()
                .filter(t -> t.getType().equalsIgnoreCase("expense"))
                .mapToDouble(Transaction::getAmount).sum();

        double netBalance = totalIncome - totalExpenses; // ✅ Define netBalance

        user.setReports(new FinancialSummary(totalIncome, totalExpenses, netBalance));
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}