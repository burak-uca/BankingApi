package com.CRUD.BankingApi.Exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long accountId) {
        super("Account not found with ID: " + accountId);
    }
}
//Bu exception, istenen hesap veritabanında bulunamadığında fırlatılır.
// Örneğin, bir kullanıcı belirli bir hesap ID'si ile işlem yapmak istediğinde,
// ancak bu ID'ye sahip bir hesap veritabanında bulunamadığında bu exception fırlatılır.