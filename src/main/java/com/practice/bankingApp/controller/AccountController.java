package com.practice.bankingApp.controller;

import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.bankingApp.dto.AccountDto;
import com.practice.bankingApp.service.AccountService;

@RestController
@RequestMapping("api/accounts")
public class AccountController 
{
    private AccountService accountService;

    public AccountController(AccountService accountService) 
    {
        this.accountService = accountService;
    }

    // Add account By passing requestbody
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) 
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get account by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) 
    {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);

    }

    // Put for deposit amount
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(
            @PathVariable Long id,
            @RequestBody Map<String, Double> request) 
    {

        Double amount = request.get("amount");

        if (amount == null) 
        {
            return ResponseEntity.badRequest().body(null); // Return 400 if amount is not provided
        }

        if (amount <= 0) 
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Return 400 if amount is <= 0
        }

        try 
        {
            AccountDto accountDto = accountService.deposit(id, amount);
            return ResponseEntity.ok(accountDto); // Return 200 with account details
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if account not found
        }
    }

    @PutMapping({"/{id}/withdraw"})
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double>request)
    {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }
}
