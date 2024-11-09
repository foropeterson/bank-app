package com.example.banking_system.service;

import com.example.banking_system.entities.Account;
import com.example.banking_system.entities.Transactions;
import com.example.banking_system.exception.CustomException;
import com.example.banking_system.repository.AccountRepository;
import com.example.banking_system.repository.TransactionRepository;
import com.example.banking_system.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transactions transact(Transactions transactions) {

        Optional<Account> checkAccountNumber = accountRepository.findByAccountNumber(transactions.getAccountNumber());
        transactions.setTransactionDate(LocalDateTime.now());
        if (!checkAccountNumber.isPresent()) {
            throw new CustomException(new ErrorResponse(404, "not Found", "Account does not exist"));
        }
        if (transactions.getTransactionType().equalsIgnoreCase("Deposit")) {

            transactionRepository.save(transactions);
            // to increase balance
            accountRepository.updateBalance(transactions.getAccountNumber(), transactions.getAmount());

            return transactions;
        } else if (transactions.getTransactionType().equalsIgnoreCase("Withdraw"))
            if (checkAccountNumber.get().getBalance() < transactions.getAmount()) {
                throw new CustomException(new ErrorResponse(411, "insufficient Balance", "You have insufficient balance"));
            }
        // save transaction
        transactionRepository.save(transactions);
        // to increase balance
        accountRepository.withdrawMoney(transactions.getAccountNumber(), transactions.getAmount());

        return transactions;
    }
}


