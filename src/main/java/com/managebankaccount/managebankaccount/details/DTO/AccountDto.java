package com.managebankaccount.managebankaccount.details.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class AccountDto implements Serializable {
    private Long idAccount;

    private Long accountNumber;

    private String userId;

    private String password;

    private Double balanceAmount;
}
