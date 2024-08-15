package com.CRUD.BankingApi.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    // Many-to-One ilişki - Gönderen hesap
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_account_id", nullable = false)
    private Account fromAccount;

    // Many-to-One ilişki - Alıcı hesap
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_account_id", nullable = false)
    private Account toAccount;

    // Constructor
    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(fromAccount, that.fromAccount) &&
                Objects.equals(toAccount, that.toAccount) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromAccount, toAccount, amount, timestamp);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}