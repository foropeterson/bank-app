package com.example.banking_system.repository;

import com.example.banking_system.entities.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer > {
    Optional<Account> findByAccountNumber(int accountNumber);
    Account getAccountByAccountNumber(int accountNumber);
    @Query(value = "select balance from account where account_number=:accountNumber",
    nativeQuery = true)
    Integer getBalance(int accountNumber);
    @Modifying
    @Transactional
    @Query(value = "update account set balance = balance + :amount  where account_number=:accountNumber",nativeQuery = true)
    Integer updateBalance(int accountNumber, double amount);
    @Modifying
    @Transactional
    @Query(value = "update account set balance = balance - :amount  where account_number=:accountNumber",nativeQuery = true)
    Integer withdrawMoney(int accountNumber, double amount);
}
