package com.magd.backend_bank.service.impl;

import com.magd.backend_bank.dto.AccountDto;
import com.magd.backend_bank.entity.Account;
import com.magd.backend_bank.exception.InsufficientBalanceException;
import com.magd.backend_bank.exception.ResourceNotFoundException;
import com.magd.backend_bank.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;
    private AccountDto accountDto;
    private Account account2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        account = new Account();
        account.setId(1L);
        account.setAccountHolderName("Ali Majidi");
        account.setBalance(100.0);
        accountDto = new AccountDto(1L,"Ali Majidi",100.0);

        account2 = new Account();
        account2.setId(2L);
        account2.setAccountHolderName("Pris Joss");
        account2.setBalance(500.0);

    }

    @Test
    void createAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDto result = accountService.createAccount(accountDto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Ali Majidi", result.accountHoldName());
        assertEquals(100.0, result.balance());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void getAccountById() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        AccountDto result = accountService.getAccountById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Ali Majidi", result.accountHoldName());
        assertEquals(100.0, result.balance());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void getAccountById_ResourceNotFoundException() {
        when(accountRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountService.getAccountById(3L));

        verify(accountRepository, times(1)).findById(3L);
    }

    @Test
    void deposit() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        AccountDto result = accountService.deposit(1L, 50.0);

        assertNotNull(result);
        assertEquals(150.0, result.balance());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void withdraw() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        AccountDto result = accountService.withdraw(1L, 50.0);

        assertNotNull(result);
        assertEquals(50.0, result.balance());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void withdraw_InsufficientBalanceException() {
        account.setBalance(30.0);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(InsufficientBalanceException.class, () -> accountService.withdraw(1L, 50.0));

        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void getAllAccounts() {
        List<Account> accounts = List.of(account,account2);
        when(accountRepository.findAll()).thenReturn(accounts);

        List<AccountDto> result = accountService.getAllAccounts();

        assertEquals(2, result.size());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void deleteAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        doNothing().when(accountRepository).deleteById(1L);

        accountService.deleteAccount(1L);

        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteAccount_ResourceNotFoundException() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountService.deleteAccount(5L));

        verify(accountRepository, times(1)).findById(5L);
    }
}
