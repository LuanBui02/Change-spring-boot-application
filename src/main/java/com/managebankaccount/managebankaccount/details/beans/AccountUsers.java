package com.managebankaccount.managebankaccount.details.beans;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountUsers {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAccount;

    private Long accountNumber;

    private String userId;

    private String password;

    private Double balanceAmount;
}
