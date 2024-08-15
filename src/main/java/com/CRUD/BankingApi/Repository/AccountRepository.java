package com.CRUD.BankingApi.Repository;

import com.CRUD.BankingApi.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
