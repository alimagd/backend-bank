package com.magd.backend_bank.mapper;

import com.magd.backend_bank.dto.AccountDto;
import com.magd.backend_bank.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        return new Account(
                accountDto.id(),
                accountDto.accountHoldName(),
                accountDto.balance()
        );
    }

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }

}
