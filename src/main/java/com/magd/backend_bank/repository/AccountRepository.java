package com.magd.backend_bank.repository;

import com.magd.backend_bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
