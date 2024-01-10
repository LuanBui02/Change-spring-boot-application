package com.managebankaccount.managebankaccount.details.service;

import com.managebankaccount.managebankaccount.details.DTO.AccountDto;
import com.managebankaccount.managebankaccount.details.beans.AccountUsers;
import com.managebankaccount.managebankaccount.details.beans.Users;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
public interface AccountService {
    void addAccount(AccountDto account);
    AccountDto updateAccount(int idAccount, AccountDto account);
    void removeAccount(long idAccount);
    List<AccountDto> showAllAccount();
    AccountDto depositMoney(int id, AccountDto account, double deposit);
    AccountDto withDrawMoney(AccountDto account,
                                     int idAccount,
                                     @RequestParam double withDraw);
    AccountUsers findAccountByName(String name);
    AccountUsers findAccountByBirthday(String birthday);
    void addUsers(Users user);
    List<Users> showUser();
    void removeUser(int id);
}
