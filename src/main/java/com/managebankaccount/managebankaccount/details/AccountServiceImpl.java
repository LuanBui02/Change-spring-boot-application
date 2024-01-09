package com.managebankaccount.managebankaccount.details;

import com.managebankaccount.managebankaccount.details.advice.AlreadyReported;
import com.managebankaccount.managebankaccount.details.advice.ConditionWithDraw;
import com.managebankaccount.managebankaccount.details.advice.NoSpaceInPassword;
import com.managebankaccount.managebankaccount.details.beans.AccountUsers;
import com.managebankaccount.managebankaccount.details.beans.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class AccountServiceImpl {

    public List<AccountUsers> accountUsers = new ArrayList<>();

    List<User> users = new ArrayList<>();

    public AccountUsers addAccount(AccountUsers account) {
        for (AccountUsers accountUser : accountUsers) {
            if (account.getIdAccount() == accountUser.getIdAccount()) {
                throw new AlreadyReported(accountUser.getIdAccount());
            }
        }
        String password = account.getPassword();
        if (password.trim().isEmpty()) {
            throw new NullPointerException("Password can not empty");
        }
        for (char ch : password.toCharArray()) {
            if (ch == ' ') {
                throw new NoSpaceInPassword();
            }
        }
        accountUsers.add(account);

        return account;
    }

    public AccountUsers updateAccount(int idAccount, AccountUsers account) {
        if (accountUsers.isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            String password = account.getPassword();
            if (password.trim().isEmpty()) {
                throw new NullPointerException("Password can not empty");
            }
            for (char ch : password.toCharArray()) {
                if (ch == ' ') {
                    throw new NoSpaceInPassword();

                } else {
                    account.setPassword(password);
                }
            }
            for (int i = 0; i < accountUsers.size(); i++) {
                if (idAccount == accountUsers.get(i).getIdAccount()) {
                    accountUsers.get(i).setAccountNumber(account.getAccountNumber());
                    accountUsers.get(i).setUserId(account.getUserId());
                    accountUsers.get(i).setPassword(account.getPassword());
                }
            }
        }
        return account;
    }

    public void removeAccount(long idAccount) {
        if (accountUsers.isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            for (int i = 0; i < accountUsers.size(); i++) {
                if (idAccount == accountUsers.get(i).getIdAccount()) {
                    accountUsers.remove(accountUsers.get(i));
                }
            }
        }
    }

    public void removeUser(int id) {
        if (users.isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (id == users.get(i).getId()) {
                    users.remove(users.get(i));
                }
            }
        }
    }

    public List<AccountUsers> showAllAccount() {
        return accountUsers;
    }

    public List<AccountUsers> depositMoney(int idAccount,
                                           AccountUsers account,
                                           @RequestParam double deposit) {
        if (accountUsers.isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            for (int i = 0; i < accountUsers.size(); i++) {
                if (idAccount == accountUsers.get(i).getIdAccount()) {
                    double depositMoney = deposit + accountUsers.get(i).getBalanceAmount();
                    accountUsers.get(i).setBalanceAmount(depositMoney);
                }
            }
        }
        return accountUsers;
    }


    public List<AccountUsers> withDrawMoney(AccountUsers account,
                                            int idAccount,
                                            @RequestParam double withDraw) {
        if (accountUsers.isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            for (int i = 0; i < accountUsers.size(); i++) {
                if (idAccount == accountUsers.get(i).getIdAccount()) {
                    if (accountUsers.get(i).getBalanceAmount() >= withDraw) {
                        double withDrawMoney = accountUsers.get(i).getBalanceAmount() - withDraw;
                        accountUsers.get(i).setBalanceAmount(withDrawMoney);
                    } else {
                        throw new ConditionWithDraw();
                    }
                }

            }
        }
        return accountUsers;
    }


    public void addUsers(User user) {
        for (User check : users) {
            user.setId(user.getId());
            if (check.getId() == user.getId()) {
                throw new AlreadyReported(user.getId());
            }
        }
        users.add(user);
    }

    public List<User> showUser() {
        return users;
    }

    public AccountUsers findAccountByName(@RequestParam String name) {
        AccountUsers account = new AccountUsers();
        if (accountUsers.isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else if (users.isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            boolean checkTrue = false;
            for (int i = 0; i < users.size(); i++) {

                String nameUpperCase = name.toUpperCase();
                String nameUser = users.get(i).getName().toUpperCase();

                if (nameUser.equals(nameUpperCase)) {
                    account = accountUsers.get(i);
                    checkTrue = true;
                    break;
                }
            }
            if(!checkTrue) {
                throw new NullPointerException(Constant.noName);
            }

        }
        return account;
    }

    public AccountUsers findAccountByBirthday(@RequestParam String birthday) {
        AccountUsers account = new AccountUsers();
        if (accountUsers.isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else if (users.isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            boolean checkTrue = false;
            for (int i = 0; i < users.size(); i++) {
                String birthdayUpperCase = birthday.toUpperCase();
                String birthdayUser = users.get(i).getBirthday().toUpperCase();
                if (birthdayUpperCase.equals(birthdayUser)) {
                    account = accountUsers.get(i);
                    checkTrue = true;
                    break;
                }
            }
            if(!checkTrue) {
                throw new NullPointerException(Constant.noBirthday);
            }
        }
        return account;
    }

}
