package com.finace;



public class Transaction {
    private String id;
    private String type; // "income" or "expense"
    private double amount;
    private String description; // "Salary", "Groceries",
    private String date; // "2024-06-15"

    public Transaction() {
    }

    public Transaction(String id, String type, double amount, String description, String date) {
        this.id = id;
        setType(type);
        setAmount(amount);
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(type.equals("income") || type.equals("expense")) {
            this.type=type;
        } else {
            throw new IllegalArgumentException("Type must be 'income' or 'expense'");
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if(amount >= 0){
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return "Transaction{id='" + id + "', type='" + type + "', amount=" + amount +
               ", description='" + description + "', date='" + date + "'}";
    }
}
