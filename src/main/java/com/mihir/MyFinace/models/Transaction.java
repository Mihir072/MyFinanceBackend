package com.mihir.MyFinace.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.UUID;


@Document(collection = "transactions") // Optional if transactions are stored separately
public class Transaction {

    @Id  // ✅ MongoDB will now generate an `id`
    private String id;

    private String txnId; // Unique transaction ID
    private double amount;
    private String type; // "income" or "expense"
    private Date date;
    private String category;

    public Transaction() {
        this.txnId = UUID.randomUUID().toString();
        this.date = new Date();
    }

    public Transaction(double amount, String type, String category) {
        this.txnId = UUID.randomUUID().toString();
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = new Date();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTxnId() { return txnId; }
    public void setTxnId(String txnId) { this.txnId = txnId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
