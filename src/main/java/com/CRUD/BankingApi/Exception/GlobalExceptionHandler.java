package com.CRUD.BankingApi.Exception;
//Bu sınıf, uygulamanızda meydana gelen hataları merkezi bir yerde yakalamak ve bunları yönetmek için kullanılır.
// Spring'de bu tür bir sınıf oluşturmak için @ControllerAdvice anotasyonu kullanılır.
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//Bu anotasyon, sınıfı bir "global advice" olarak işaretler
//yani uygulamanın herhangi bir yerinde meydana gelen hataları dinler ve yönetir.
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFound(AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFunds(InsufficientFundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Yetersiz bakiye: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Beklenmedik bir hata oluştu: " + ex.getMessage());
    }
}
