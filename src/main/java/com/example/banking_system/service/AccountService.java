package com.example.banking_system.service;

import com.example.banking_system.entities.Account;
import com.example.banking_system.exception.CustomException;
import com.example.banking_system.repository.AccountRepository;
import com.example.banking_system.response.ErrorResponse;
import com.example.banking_system.utils.LoggerUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final LoggerUtil loggerUtil;

    public AccountService(AccountRepository accountRepository, LoggerUtil loggerUtil) {
        this.accountRepository = accountRepository;
        this.loggerUtil = loggerUtil;
    }

    public Account createAccount(Account account) {
        long start= System.nanoTime();
        //String process, int responseCode, String responseMessage, String errorMessage, long timeTaken
        loggerUtil.info("create account",200,"creating account","",System.nanoTime()-start);
        account.setCreatedAt(LocalDateTime.now());
        Optional<Account> accountExist = accountRepository.findByAccountNumber(account.getAccountNumber());
        if (accountExist.isPresent()) {
            loggerUtil.error("create account",409,"account exist","error creating account. Acccount exist",System.nanoTime()-start);
            throw new CustomException(new ErrorResponse(409, "Duplicate", "Account Already exist"));
        }
        loggerUtil.info("create account",200,"account created succcessfully","",System.nanoTime()-start);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findByAccountNumber(int accountNumber) {

        Optional<Account> accountRecord = accountRepository.findByAccountNumber(accountNumber);
        if (!accountRecord.isPresent()) {
            throw new CustomException(new ErrorResponse(404, "Not Found", "Record Not found"));
        }
        return accountRecord.get();
    }
}
