package com.magd.backend_bank.controller;

import com.magd.backend_bank.dto.AccountDto;
import com.magd.backend_bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    // Add Account Rest Api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        AccountDto savedAccountDto = accountService.createAccount(accountDto);
        return new ResponseEntity<>(savedAccountDto, HttpStatus.CREATED);
    }

    // Get Account Rest Api by id
    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long accountId) {
        AccountDto accountDto = accountService.getAccountById(accountId);
        return ResponseEntity.ok(accountDto);
        //        return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);

        /*
        *
Use ResponseEntity.ok() when you want to keep it simple and return a 200 OK status with a body.
Use new ResponseEntity<>(...) when you need more explicit control, such as adding headers or using non-200 status codes.
* e.g.
HttpHeaders headers = new HttpHeaders();
headers.set("Custom-Header", "value");
return new ResponseEntity<>(accountService.getAccountById(accountId), headers, HttpStatus.OK);

        *  */
    }

    // Deposit Rest Api
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request) {

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // withdraw Rest Api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request) {

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Get All Rest Api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }

    // Delete by id Rest Api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        accountService.deleteAccount(id);
        return ResponseEntity.ok(String.format("Account with id = %d removed.. ",id));
//        return ResponseEntity.ok("Account with id = " + id + " removed.. ");

    }


}
