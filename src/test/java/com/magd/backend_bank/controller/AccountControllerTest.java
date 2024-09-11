package com.magd.backend_bank.controller;

import com.magd.backend_bank.dto.AccountDto;
import com.magd.backend_bank.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mocks and injects them
    }

    @Test
    void testAddAccount() {
        AccountDto accountDto = new AccountDto(1L,"Aba Joan",10054.0);

        when(accountService.createAccount(any(AccountDto.class))).thenReturn(accountDto);

        ResponseEntity<AccountDto> response = accountController.addAccount(accountDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
        verify(accountService, times(1)).createAccount(accountDto);
    }

    @Test
    void testGetAccountById() {
        AccountDto accountDto = new AccountDto(1L,"Aba Joan",10054.0);

        when(accountService.getAccountById(1L)).thenReturn(accountDto);

        ResponseEntity<AccountDto> response = accountController.getAccountById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
        verify(accountService, times(1)).getAccountById(1L);
    }

    @Test
    void testDeposit() {
        AccountDto accountDto = new AccountDto(1L,"Aba Joan",10054.0);


        when(accountService.deposit(eq(1L), anyDouble())).thenReturn(accountDto);

        ResponseEntity<AccountDto> response = accountController.deposit(1L, Map.of("amount", 200.0));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
        verify(accountService, times(1)).deposit(1L, 200.0);
    }

    @Test
    void testWithdraw() {
        AccountDto accountDto = new AccountDto(1L,"Aba Joan",10054.0);

        when(accountService.withdraw(eq(1L), anyDouble())).thenReturn(accountDto);

        ResponseEntity<AccountDto> response = accountController.withdraw(1L, Map.of("amount", 50.0));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
        verify(accountService, times(1)).withdraw(1L, 50.0);
    }

    @Test
    void testGetAllAccounts() {
        AccountDto account1 = new AccountDto(1L,"Aba Joan",10054.0);
        AccountDto account2 = new AccountDto(1L,"Fatima Maj",7500.0);

        List<AccountDto> accountDtos = Arrays.asList(account1, account2);

        when(accountService.getAllAccounts()).thenReturn(accountDtos);

        ResponseEntity<List<AccountDto>> response = accountController.getAllAccounts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDtos, response.getBody());
        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    void testDeleteAccount() {
        doNothing().when(accountService).deleteAccount(1L);

        ResponseEntity<String> response = accountController.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account with id = 1 removed.. ", response.getBody());
        verify(accountService, times(1)).deleteAccount(1L);
    }
}