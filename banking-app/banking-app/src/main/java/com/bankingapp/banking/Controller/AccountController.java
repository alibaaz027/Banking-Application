package com.bankingapp.banking.Controller;

import com.bankingapp.banking.Dto.AccountDto;
import com.bankingapp.banking.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add account Rest API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        AccountDto accountDto= accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit( @PathVariable Long id,
                                              @RequestBody Map<String,Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto =accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    // Withdraw REST API

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                              @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    // Get All Accounts REST API

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){

        List<AccountDto> accountDto= accountService.getAllAccount();
        return ResponseEntity.ok(accountDto);
    }

    // Delete REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccounts(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is Deleted Successfully!");
    }
}
