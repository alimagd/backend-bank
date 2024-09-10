package com.magd.backend_bank.service.impl;

import com.magd.backend_bank.dto.AccountDto;
import com.magd.backend_bank.entity.Account;
import com.magd.backend_bank.exception.InsufficientBalanceException;
import com.magd.backend_bank.exception.ResourceNotFoundException;
import com.magd.backend_bank.mapper.AccountMapper;
import com.magd.backend_bank.repository.AccountRepository;
import com.magd.backend_bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exists with id: " + id));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exists with id: " + id));
        account.setBalance(account.getBalance() + amount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exists with id: " + id));
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient Balance.. ");
        }
            account.setBalance(account.getBalance() - amount);
            Account savedAccount = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .toList();

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exists with id: " + id));
        accountRepository.deleteById(id);
    }
}
