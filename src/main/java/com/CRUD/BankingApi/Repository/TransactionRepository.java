package com.CRUD.BankingApi.Repository;

import com.CRUD.BankingApi.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccountIdOrToAccountId(Long fromAccountId, Long toAccountId);
}
