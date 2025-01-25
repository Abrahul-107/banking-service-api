package com.practice.bankingApp.mapper;

import com.practice.bankingApp.dto.AccountDto;
import com.practice.bankingApp.entity.Account;

public class AccountMapper 
{
    @SuppressWarnings("deprecation")
    public static Account mapToAccount(AccountDto accountDto)
    {
        Account account = new Account(
            accountDto.getId(),
            accountDto.getAccountHolderName(),
            accountDto.getBalance()
        );
                return account;
    }
    public static AccountDto mapToAccountDto(@SuppressWarnings("deprecation") Account account)
    {
        @SuppressWarnings("deprecation")
        AccountDto accountDto = new AccountDto(
            account.getId(),
            account.getAccountHolderName(),
            account.getBalance()
        );
        return accountDto;
    }
}
