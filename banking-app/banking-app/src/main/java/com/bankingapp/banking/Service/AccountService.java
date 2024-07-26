package com.bankingapp.banking.Service;

import com.bankingapp.banking.Dto.AccountDto;
import com.bankingapp.banking.Entity.Account;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id , double amount);

    List<AccountDto> getAllAccount();

    void deleteAccount(Long id);
}
