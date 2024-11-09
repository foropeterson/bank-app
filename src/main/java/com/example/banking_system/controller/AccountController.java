package com.example.banking_system.controller;

import com.example.banking_system.entities.Account;
import com.example.banking_system.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @GetMapping("/accounts/{accountNumber}")
    public Account getAccount(@PathVariable("accountNumber") int accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }
}
