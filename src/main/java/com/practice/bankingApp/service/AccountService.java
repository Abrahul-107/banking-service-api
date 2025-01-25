package com.practice.bankingApp.service;

import com.practice.bankingApp.dto.AccountDto;

public interface AccountService 
{
    AccountDto createAccount(AccountDto account);
    
    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id,Double amount);

    AccountDto withdraw(Long id, Double amount);
    
}
