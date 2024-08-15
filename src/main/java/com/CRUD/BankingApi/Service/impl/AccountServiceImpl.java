package com.CRUD.BankingApi.Service.impl;

import com.CRUD.BankingApi.Model.Account;
import com.CRUD.BankingApi.Model.Transaction;
import com.CRUD.BankingApi.Repository.AccountRepository;
import com.CRUD.BankingApi.Repository.TransactionRepository;
import com.CRUD.BankingApi.Service.AccountService;
import com.CRUD.BankingApi.Exception.AccountNotFoundException;
import com.CRUD.BankingApi.Exception.InsufficientFundsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Account createAccount(String firstName, String lastName, double initialBalance) {
        Account account = new Account(firstName, lastName, initialBalance);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    @Override
    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId);
    }

    @Override
    @Transactional // Bu metodun bir transaction olarak çalışmasını sağlar
    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException();
        }

        // Gönderen hesaptan para çek
        fromAccount.setBalance(fromAccount.getBalance() - amount);

        // Alıcı hesaba para yatır
        toAccount.setBalance(toAccount.getBalance() + amount);

        // Hesapları güncelle
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // İşlem kaydını oluştur ve kaydet - fromAccount ve toAccount doğru bir şekilde set ediliyor
        Transaction transaction = new Transaction(fromAccount, toAccount, amount);
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionHistory(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Transaction> transactions = transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId);

        if (startDate != null && endDate != null) {
            return transactions.stream()
                    .filter(t -> t.getTimestamp().isAfter(startDate) && t.getTimestamp().isBefore(endDate))
                    .collect(Collectors.toList());
        }

        return transactions;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional // Hesap güncelleme işlemi de bir transaction olarak çalışır
    public Account updateAccount(Long accountId, String firstName, String lastName) {
        Account account = getAccount(accountId);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        return accountRepository.save(account);
    }

    @Override
    @Transactional // Hesap silme işlemi de bir transaction olarak çalışır
    public void deleteAccount(Long accountId) {
        Account account = getAccount(accountId);
        accountRepository.delete(account);
    }
}
