package com.bankingapp.banking.Service.Impl;

import com.bankingapp.banking.Dto.AccountDto;
import com.bankingapp.banking.Entity.Account;
import com.bankingapp.banking.Mapper.AccountMapper;
import com.bankingapp.banking.Repository.AccountRepository;
import com.bankingapp.banking.Service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        //Vice versa AccountDto to AccountJpa
        Account account = AccountMapper.mapToAccount(accountDto);

        //Saved into Database
        Account savedAccount = accountRepository.save(account);

        // AccountJpa to AccountDto return back from DB and return to Mapper class then dto back to Jason
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto getAccountById(Long id) {

       Account account= accountRepository
               .findById(id)
               .orElseThrow(() -> new RuntimeException("Account doest not exists") );
       //AccountJpa to AccountDto
       return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doest not exists") );
        double total = account.getBalance()+ amount;
        account.setBalance(total);

        Account saveAccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doest not exists") );
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);

        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts= accountRepository.findAll();
        return accounts.stream().map((account -> AccountMapper.mapToAccountDto(account)))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doest not exists") );

        accountRepository.deleteById(id);

    }
}
