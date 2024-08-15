package com.CRUD.BankingApi.Service;

import com.CRUD.BankingApi.Model.Account;
import com.CRUD.BankingApi.Model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountService {


    Account createAccount(String firstName, String lastName, double initialBalance);

    Account getAccount(Long accountId);

    List<Transaction> getTransactionHistory(Long accountId);

    void transfer(Long fromAccountId, Long toAccountId, double amount);

    List<Transaction> getTransactionHistory(Long accountId, LocalDateTime startDate, LocalDateTime endDate);

    List<Account> getAllAccounts();

    Account updateAccount(Long accountId, String firstName, String lastName);

    void deleteAccount(Long accountId);
}

