package com.CRUD.BankingApi.Exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
        super("Insufficient funds for this transaction.");
    }
}
//Bu exception, bir hesaptan yapılacak transferde yeterli bakiye olmadığında fırlatılır.
// Örneğin, bir kullanıcı belirli bir miktar para transferi yapmak istediğinde,
// ancak hesabında yeterli bakiye bulunmadığında bu exception fırlatılır.