package com.practice.bankingApp.service.impl;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.practice.bankingApp.dto.AccountDto;
import com.practice.bankingApp.entity.Account;
import com.practice.bankingApp.mapper.AccountMapper;
import com.practice.bankingApp.repository.AccountRepository;
import com.practice.bankingApp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService
{
    private AccountRepository accountRepository;

    
    public AccountServiceImpl(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto)
    {
        @SuppressWarnings( "deprecation" )
        Account account = AccountMapper.mapToAccount(accountDto);
        @SuppressWarnings("deprecation")
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id)
    {
        @SuppressWarnings("deprecation")
        Account account = accountRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Account does not exist"));
        
        
        return AccountMapper.mapToAccountDto(account);
    }

    @SuppressWarnings("deprecation")
    @Override
    public AccountDto deposit(Long id,Double amount)
    {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't exists"));
        double total = account.getBalance() + amount;
        System.out.println(total);
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);


    }

    @SuppressWarnings("deprecation")
    @Override
    public AccountDto withdraw(Long id,Double amount)
    {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
        if(amount>account.getBalance())
        {
            throw new RuntimeErrorException(null, "Insufficient balance");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);

    }

}
