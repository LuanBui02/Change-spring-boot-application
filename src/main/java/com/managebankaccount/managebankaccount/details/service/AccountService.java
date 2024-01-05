package com.managebankaccount.managebankaccount.details.service;

import com.managebankaccount.managebankaccount.details.beans.AccountUsers;
import com.managebankaccount.managebankaccount.details.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AccountService extends JpaRepository<AccountUsers, Long>{
    AccountUsers addAccount(AccountUsers account);
    List<AccountUsers> updateAccount(int idAccount, AccountUsers account);
    void removeAccount(long idAccount);
    List<AccountUsers> showAllAccount();
    List<AccountUsers> depositMoney(int id,
                                    AccountUsers account,
                                    double deposit);
    List<AccountUsers> withDrawMoney(AccountUsers account,
                                     int idAccount,
                                     @RequestParam double withDraw);
    AccountUsers findAccountByName(String name);
    AccountUsers findAccountByBirthday(String birthday);
    void addUsers(User user);
    List<User> showUser();
    void removeUser(int id);
}
