package com.practice.bankingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.bankingApp.entity.Account;

@SuppressWarnings("deprecation")
public interface AccountRepository extends JpaRepository<Account,Long>
{

    
}
