package com.CRUD.BankingApi.Controller;

import com.CRUD.BankingApi.DTO.CreateAccountRequest;
import com.CRUD.BankingApi.DTO.TransferRequest;
import com.CRUD.BankingApi.Model.Account;
import com.CRUD.BankingApi.Model.Transaction;
import com.CRUD.BankingApi.Service.AccountService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

// Bu metot, yeni bir banka hesabı oluşturur.

    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest request) {
        Account account = accountService.createAccount(request.getFirstName(), request.getLastName(), request.getInitialBalance());
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

// Bu metot, sistemdeki tüm hesapları döner.
// accountService.getAllAccounts metodu çağrılarak, veritabanındaki tüm hesaplar listelenir.

    @GetMapping("/get-all-accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

// Bu metot, belirli bir accountId'ye sahip olan hesabın detaylarını döner.

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccount(accountId);
        return ResponseEntity.ok(account);
    }

//Bu metot, mevcut bir hesabın bilgilerini günceller.

    @PutMapping("/update-account/{accountId}")
    public ResponseEntity<Account>  updateAccount(
            @PathVariable Long accountId,
            @RequestBody CreateAccountRequest request) {
        Account updatedAccount = accountService.updateAccount(accountId, request.getFirstName(), request.getLastName());
        return ResponseEntity.ok(updatedAccount);
    }

//Bu metot, belirli bir hesabı siler.

    @DeleteMapping("/delete-account/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build(); // 204 No Content, başarıyla silindi
    }

// Bu metot, belirli bir accountId'ye sahip hesabın tüm transfer geçmişini döner.

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long accountId) {
        List<Transaction> transactions = accountService.getTransactionHistory(accountId);
        return ResponseEntity.ok(transactions);
    }

//Bu metot, iki hesap arasında para transferi yapar. TransferRequest DTO'su aracılığıyla gerekli veriler
// (fromAccountId, toAccountId, amount) alınır ve accountService.transfer metodu çağrılır.

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest request) {
        accountService.transfer(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
        return ResponseEntity.ok().build();
    }

//Bu metot, belirli bir accountId'ye sahip hesabın belirli bir tarih aralığındaki transfer geçmişini döner.
// startDate ve endDate parametreleri isteğe bağlı olarak alınır ve varsa bu aralıkta filtreleme yapılır.

    @GetMapping("/{accountId}/transactions-according-to-time")
    public ResponseEntity<List<Transaction>> getTransactionHistory(
            @PathVariable Long accountId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Transaction> transactions = accountService.getTransactionHistory(accountId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }
}