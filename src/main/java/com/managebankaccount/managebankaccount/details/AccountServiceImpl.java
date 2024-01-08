package com.managebankaccount.managebankaccount.details;

import com.managebankaccount.managebankaccount.details.beans.AccountUsers;
import com.managebankaccount.managebankaccount.details.beans.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class AccountServiceImpl {

    List<AccountUsers> accountUsers = new ArrayList<>();

    List<User> users = new ArrayList<>();

    public AccountUsers addAccount(AccountUsers account) {
        for (AccountUsers check : accountUsers) {
            account.setIdAccount(account.getIdAccount());
            if (check.getIdAccount() == account.getIdAccount()) {
                throw new RuntimeException(Constant.sameId);
            }
        }
        String password = account.getPassword();
        if (password.trim().isEmpty()) {
            throw new RuntimeException(Constant.noPassword);
        }
        for (char ch : password.toCharArray()) {
            if (ch == ' ') {
                throw new RuntimeException(Constant.noSpaceInPassword);
            } else {
                account.setPassword(password);
            }
        }
        accountUsers.add(account);

        return account;
    }

    public List<AccountUsers> updateAccount(int idAccount, AccountUsers account) {
        if (accountUsers.isEmpty()) {
            throw new RuntimeException(Constant.emptyList);
        } else {
            String password = account.getPassword();
            if (password.trim().isEmpty()) {
                throw new RuntimeException(Constant.noPassword);
            }
            for (char ch : password.toCharArray()) {
                if (ch == ' ') {
                    throw new RuntimeException(Constant.noSpaceInPassword);
                } else {
                    account.setPassword(password);
                }
            }
            for (int i = 0; i < accountUsers.size(); i++) {
                if (idAccount == accountUsers.get(i).getIdAccount()) {
                    accountUsers.get(i).setAccountNumber(account.getAccountNumber());
                    accountUsers.get(i).setUserId(account.getUserId());
                    accountUsers.get(i).setPassword(account.getPassword());
                    accountUsers.get(i).setBalanceAmount(account.getBalanceAmount());
                }
            }
        }

        return accountUsers;
    }

    public void removeAccount(long idAccount) {
        if (accountUsers.isEmpty()) {
            throw new RuntimeException(Constant.emptyList);
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
            throw new RuntimeException(Constant.emptyList);
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
            throw new RuntimeException(Constant.emptyList);
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
            System.out.println(Constant.emptyList);
        } else {
            for (int i = 0; i < accountUsers.size(); i++) {
                if (idAccount == accountUsers.get(i).getIdAccount()) {
                    if (accountUsers.get(i).getBalanceAmount() >= withDraw) {
                        double withDrawMoney = accountUsers.get(i).getBalanceAmount() - withDraw;
                        accountUsers.get(i).setBalanceAmount(withDrawMoney);
                    } else {
                        throw new RuntimeException(Constant.conditionWithDraw);
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
                throw new RuntimeException(Constant.sameId);
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
            throw new RuntimeException(Constant.emptyList);
        } else if (users.isEmpty()) {
            throw new RuntimeException(Constant.emptyList);
        } else {
            for (int i = 0; i < users.size(); i++) {
                String nameUpperCase = name.toUpperCase();
                String nameUser = users.get(i).getName().toUpperCase();
                if (nameUpperCase.equals(nameUser)) {
                    account = accountUsers.get(i);
                } else {
                    throw new RuntimeException("There isn't name in list");
                }
            }
        }
        return account;
    }

    public AccountUsers findAccountByBirthday(@RequestParam String birthday) {
        AccountUsers account = new AccountUsers();
        if (accountUsers.isEmpty()) {
            throw new RuntimeException(Constant.emptyList);
        } else if (users.isEmpty()) {
            throw new RuntimeException(Constant.emptyList);
        } else {
            for (int i = 0; i < users.size(); i++) {
                String birthdayUpperCase = birthday.toUpperCase();
                String birthdayUser = users.get(i).getBirthday().toUpperCase();
                if (birthdayUpperCase.equals(birthdayUser)) {
                    account = accountUsers.get(i);
                } else {
                    throw new RuntimeException("There isn't birthday in list");
                }
            }
        }
        return account;
    }

}
