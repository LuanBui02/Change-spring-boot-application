package com.managebankaccount.managebankaccount.details.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Entity
public class AccountUsers {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idAccount;
    private long accountNumber;
    private String userId;
    private String password;
    private double balanceAmount;
    public AccountUsers() {

    }
    public AccountUsers(long idAccount, long accountNumber, String userId, String password, double balanceAmount) {
        this.idAccount = idAccount;
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.password = password;
        this.balanceAmount = balanceAmount;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "id=" + idAccount +
                ", accountNumber=" + accountNumber +
                ", userId='" + userId +
                ", password=" + password +
                ", balanceAmount=" + balanceAmount +
                '}';
    }
}
