package com.bankingapp.banking.Mapper;

import com.bankingapp.banking.Dto.AccountDto;
import com.bankingapp.banking.Entity.Account;

public class AccountMapper {

    //AccountDto into AccountJpa
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    //AccountJpa into AccountDto

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
          account.getId(),
          account.getAccountHolderName(),
          account.getBalance()
        );
        return accountDto;
    }
}

