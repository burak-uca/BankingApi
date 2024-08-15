package com.CRUD.BankingApi.DTO;
//DTO'lar, veriyi bir yerden başka bir yere taşırken gerekli olan minimum veriyi içerir.
// Bu, veri transferini daha verimli hale getirir ve veritabanı modellerinden bağımsız bir yapı sunar.
public class CreateAccountRequest {

    private String firstName;
    private String lastName;
    private double initialBalance;

    // Constructor
    public CreateAccountRequest() {}

    public CreateAccountRequest(String firstName, String lastName, double initialBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.initialBalance = initialBalance;
    }

    // Getters and Setters
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

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }
}
