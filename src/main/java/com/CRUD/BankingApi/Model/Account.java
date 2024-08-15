package com.CRUD.BankingApi.Model;


import jakarta.persistence.*;
import java.util.Objects;
import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "balance", nullable = false)
    private double balance;

    // Gönderen olarak ilişkilendirilen işlemler
    @OneToMany(mappedBy = "fromAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> fromTransactions;

    // Alıcı olarak ilişkilendirilen işlemler
    @OneToMany(mappedBy = "toAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> toTransactions;

    // Default Constructor
    public Account() {}

    // Constructor with parameters
    public Account(String firstName, String lastName, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 &&
                Objects.equals(id, account.id) &&
                Objects.equals(firstName, account.firstName) &&
                Objects.equals(lastName, account.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }
}