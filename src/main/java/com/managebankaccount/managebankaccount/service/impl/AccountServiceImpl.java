package com.managebankaccount.managebankaccount.service.impl;

import com.managebankaccount.managebankaccount.utils.Constant;
import com.managebankaccount.managebankaccount.DTO.AccountDto;
import com.managebankaccount.managebankaccount.beans.Users;
import com.managebankaccount.managebankaccount.accountException.AlreadyReportedException;
import com.managebankaccount.managebankaccount.accountException.ConditionWithDrawException;
import com.managebankaccount.managebankaccount.accountException.NoSpaceInPasswordException;
import com.managebankaccount.managebankaccount.beans.AccountUsers;
import com.managebankaccount.managebankaccount.repository.AccountRepository;
import com.managebankaccount.managebankaccount.repository.UserRepository;
import com.managebankaccount.managebankaccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public void addAccount(AccountDto dto) {
        AccountUsers account = new AccountUsers();

        account.setIdAccount(dto.getIdAccount());
        account.setAccountNumber(dto.getAccountNumber());
        account.setUserId(dto.getUserId());
        account.setPassword(dto.getPassword());
        account.setBalanceAmount(dto.getBalanceAmount());
        for (int i = 0; i < accountRepository.findAll().size(); i++) {

            if (Objects.equals(account.getIdAccount(), accountRepository.findAll().get(i).getIdAccount())) {
                throw new AlreadyReportedException(accountRepository.findAll().get(i).getIdAccount());
            }
        }
        String password = account.getPassword();
        if (password.trim().isEmpty()) {
            throw new NullPointerException("Password can not empty");
        }
        for (char ch : password.toCharArray()) {
            if (ch == ' ') {
                throw new NoSpaceInPasswordException();
            }
        }
        accountRepository.save(account);

    }

    public AccountDto updateAccount(int idAccount, AccountDto account) {
        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            String password = account.getPassword();
            if (password.trim().isEmpty()) {
                throw new NullPointerException("Password can not empty");
            }
            for (char ch : password.toCharArray()) {
                if (ch == ' ') {
                    throw new NoSpaceInPasswordException();

                } else {
                    account.setPassword(password);
                }
            }
            for (int i = 0; i < accountRepository.findAll().size(); i++) {
                if (idAccount == accountRepository.findAll().get(i).getIdAccount()) {
                    accountRepository.findAll().get(i).setAccountNumber(account.getAccountNumber());
                    accountRepository.findAll().get(i).setUserId(account.getUserId());
                    accountRepository.findAll().get(i).setPassword(account.getPassword());
                    accountRepository.save(accountRepository.findAll().get(i));
                }
            }
        }

        return account;
    }

    public void removeAccount(long idAccount) {
        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            for (int i = 0; i < accountRepository.findAll().size(); i++) {
                if (idAccount == accountRepository.findAll().get(i).getIdAccount()) {
                    accountRepository.deleteById(accountRepository.findAll().get(i).getIdAccount());
                }
            }
        }
    }

    public List<AccountDto> showAllAccount() {
        return accountRepository.findAll().stream()
                .map(account -> {
                    AccountDto accountDto = new AccountDto();
                    accountDto.setIdAccount(account.getIdAccount());
                    accountDto.setAccountNumber(account.getAccountNumber());
                    accountDto.setPassword(account.getPassword());
                    accountDto.setUserId(account.getUserId());
                    accountDto.setBalanceAmount(account.getBalanceAmount());
                    return  accountDto;
                }).collect(Collectors.toList());
    }

    public AccountDto depositMoney(int idAccount,
                                    AccountDto account,
                                    @RequestParam double deposit) {
        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            for (int i = 0; i < accountRepository.findAll().size(); i++) {
                if (idAccount == accountRepository.findAll().get(i).getIdAccount()) {
                    double depositMoney = deposit + accountRepository.findAll().get(i).getBalanceAmount();
                    accountRepository.findAll().get(i).setBalanceAmount(depositMoney);
                    account.setIdAccount(accountRepository.findAll().get(i).getIdAccount());
                    account.setAccountNumber(accountRepository.findAll().get(i).getAccountNumber());
                    account.setUserId(accountRepository.findAll().get(i).getUserId());
                    account.setPassword(accountRepository.findAll().get(i).getPassword());
                    account.setBalanceAmount(accountRepository.findAll().get(i).getBalanceAmount());
                    accountRepository.save(accountRepository.findAll().get(i));
                }
            }
        }
        return account;
    }
    public AccountDto withDrawMoney(AccountDto account,
                                            int idAccount,
                                            @RequestParam double withDraw) {
        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            for (int i = 0; i < accountRepository.findAll().size(); i++) {
                if (idAccount == accountRepository.findAll().get(i).getIdAccount()) {
                    if (accountRepository.findAll().get(i).getBalanceAmount() >= withDraw) {
                        double withDrawMoney = accountRepository.findAll().get(i).getBalanceAmount() - withDraw;
                        accountRepository.findAll().get(i).setBalanceAmount(withDrawMoney);
                        account.setIdAccount(accountRepository.findAll().get(i).getIdAccount());
                        account.setAccountNumber(accountRepository.findAll().get(i).getAccountNumber());
                        account.setUserId(accountRepository.findAll().get(i).getUserId());
                        account.setPassword(accountRepository.findAll().get(i).getPassword());
                        account.setBalanceAmount(accountRepository.findAll().get(i).getBalanceAmount());
                        accountRepository.save(accountRepository.findAll().get(i));
                    } else {
                        throw new ConditionWithDrawException();
                    }
                }

            }
        }
        return account;
    }
    public void addUsers(Users users) {
        Users user = new Users();

        user.setId(users.getId());
        user.setName(users.getName());
        user.setAddress(users.getAddress());
        user.setBirthday(users.getBirthday());
        user.setIdNumber(users.getIdNumber());

        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if (Objects.equals(userRepository.findAll().get(i).getId(), user.getId())) {
                throw new AlreadyReportedException(user.getId());
            }
        }
        userRepository.save(user);
    }

    public List<Users> showUser() {
        return userRepository.findAll();
    }

    public void removeUser(int id) {
        if (userRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            for (int i = 0; i < userRepository.findAll().size(); i++) {
                if (id == userRepository.findAll().get(i).getId()) {
                    userRepository.deleteById(userRepository.findAll().get(i).getId());
                }
            }
        }
    }


    public AccountUsers findAccountByName(@RequestParam String name) {
        AccountUsers account = new AccountUsers();

        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else if (userRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            boolean checkTrue = false;
            for (int i = 0; i < userRepository.findAll().size(); i++) {

                String nameUpperCase = name.toUpperCase();
                String nameUser = userRepository.findAll().get(i).getName().toUpperCase();

                if (nameUser.equals(nameUpperCase)) {
                    account = accountRepository.findAll().get(i);
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
        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else if (userRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            boolean checkTrue = false;
            for (int i = 0; i < userRepository.findAll().size(); i++) {
                String birthdayUpperCase = birthday.toUpperCase();
                String birthdayUser = userRepository.findAll().get(i).getBirthday().toUpperCase();
                if (birthdayUpperCase.equals(birthdayUser)) {
                    account = accountRepository.findAll().get(i);
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
