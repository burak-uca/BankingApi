package com.CRUD.BankingApi.DTO;

public class TransferRequest {

    private Long fromAccountId;
    private Long toAccountId;
    private double amount;

    // Constructor
    public TransferRequest() {}

    public TransferRequest(Long fromAccountId, Long toAccountId, double amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
