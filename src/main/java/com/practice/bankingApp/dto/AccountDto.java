package com.practice.bankingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto
{
    private Long id;
    private String AccountHolderName;
    private Double balance;

}