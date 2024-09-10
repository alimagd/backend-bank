package com.magd.backend_bank.dto;


public record AccountDto(
        Long id,
        String accountHoldName,
        double balance
) {
}
