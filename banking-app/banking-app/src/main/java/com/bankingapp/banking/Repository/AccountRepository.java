package com.bankingapp.banking.Repository;

import com.bankingapp.banking.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
