package com.mihir.MyFinace.services;

import com.mihir.MyFinace.models.Transaction;
import com.mihir.MyFinace.models.User;
import com.mihir.MyFinace.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encrypt Password
        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public User getUserByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> getUserById(String id){
        return userRepository.findById(id);
    }

    public User addTransaction(String userId, Transaction transaction) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.getTransactions().add(transaction);
            updateFinancialSummary(user);
            return userRepository.save(user);
        }
        return null;
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

        user.setReports(new FinancialSummary(totalIncome, totalExpenses));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}