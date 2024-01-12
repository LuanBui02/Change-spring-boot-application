package com.managebankaccount.managebankaccount.service;

import com.managebankaccount.managebankaccount.DTO.AccountDto;
import com.managebankaccount.managebankaccount.beans.AccountUsers;
import com.managebankaccount.managebankaccount.beans.Users;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
public interface AccountService {
    void addAccount(AccountDto account);
    AccountUsers updateAccount(long idAccount, AccountUsers account);
    void removeAccount(long idAccount);
    List<AccountDto> showAllAccount();
    String depositMoney(long id, AccountDto account, double deposit);
    String withDrawMoney(AccountDto account,
                                     long idAccount,
                                     @RequestParam double withDraw);
    AccountUsers findAccountByName(String name );
    AccountUsers findAccountByBirthday(String birthday);
    void addUsers(Users user);
    List<Users> showUser();
    void removeUser(long id);
}
