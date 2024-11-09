package com.example.banking_system.controller;

import com.example.banking_system.entities.Account;
import com.example.banking_system.entities.Transactions;
import com.example.banking_system.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public Transactions transact(@RequestBody Transactions transactions) {
        return transactionService.transact(transactions);
    }
}
