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
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
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

        Optional<AccountUsers> findIdDB = accountRepository.findById(account.getIdAccount());
        long getIdDb = account.getIdAccount();

        if(findIdDB.isEmpty()) {
            account.setIdAccount(getIdDb);
        } else if (findIdDB.get().getIdAccount() == getIdDb) {
            throw new AlreadyReportedException(getIdDb);
        } else {
            account.setIdAccount(getIdDb);
        }

        String password = account.getPassword();
        if (password.trim().isEmpty()) {
            throw new NullPointerException(Constant.emptyPassword);
        }
        for (char ch : password.toCharArray()) {
            if (ch == ' ') {
                throw new NoSpaceInPasswordException();
            }
        }
        accountRepository.save(account);

    }

    public AccountUsers updateAccount(long idAccount, AccountUsers account) {

        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            String password = account.getPassword();

            if (password.trim().isEmpty()) {
                throw new NullPointerException(Constant.emptyPassword);
            }
            for (char ch : password.toCharArray()) {
                if (ch == ' ') {
                    throw new NoSpaceInPasswordException();

                } else {
                    account.setPassword(password);
                }
            }
            if(accountRepository.findById(idAccount).isPresent()) {
                AccountUsers newAccount = accountRepository.findById(idAccount).get();

                newAccount.setAccountNumber(account.getAccountNumber());
                newAccount.setPassword(account.getPassword());
                newAccount.setUserId(account.getUserId());

                accountRepository.save(newAccount);

            } else {
                throw new NullPointerException(Constant.noList);
            }
        }

        return account;
    }

    public void removeAccount(long idAccount) {
        if(accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        }
        if(accountRepository.findById(idAccount).isEmpty()) {
            throw new NullPointerException(Constant.noList);
        } else {
            accountRepository.deleteById(idAccount);
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

    public String depositMoney(long idAccount,
                                    AccountDto account,
                                    @RequestParam double deposit) {
        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else {
            Optional<AccountUsers> findIdDB = accountRepository.findById(account.getIdAccount());

            if(findIdDB.isEmpty()) {
                throw new NullPointerException(Constant.noList);
            } else if (idAccount == findIdDB.get().getIdAccount()) {
                double depositMoney = deposit + findIdDB.get().getBalanceAmount();
                findIdDB.get().setBalanceAmount(depositMoney);

                accountRepository.save(findIdDB.get());
            }
        }
        return String.format("BalanceAmount of account %d is: %f",idAccount,accountRepository.findById(idAccount).get().getBalanceAmount());
    }
    public String withDrawMoney(AccountDto account,
                                            long idAccount,
                                            @RequestParam double withDraw) {
        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        }
        Optional<AccountUsers> findIdDB = accountRepository.findById(account.getIdAccount());
        if(findIdDB.isEmpty()) {
            throw new NullPointerException(Constant.noList);
        }
        if(findIdDB.get().getIdAccount() == idAccount) {
            if(findIdDB.get().getBalanceAmount() >= withDraw) {
                double withDrawMoney = findIdDB.get().getBalanceAmount() - withDraw;
                findIdDB.get().setBalanceAmount(withDrawMoney);

                accountRepository.save(findIdDB.get());
            } else {
                throw new ConditionWithDrawException();
            }
        }
        return String.format("BalanceAmount of account %d is: %f",idAccount,accountRepository.findById(idAccount).get().getBalanceAmount());
    }
    public void addUsers(Users users) {
        Users user = new Users();

        user.setId(users.getId());
        user.setName(users.getName());
        user.setAddress(users.getAddress());
        user.setBirthday(users.getBirthday());
        user.setIdNumber(users.getIdNumber());

        Optional<Users> findIdDB = userRepository.findById(users.getId());
        long getIdDb = users.getId();

        if(findIdDB.isEmpty()) {
            users.setId(getIdDb);
        } else if (findIdDB.get().getId() == getIdDb) {
            throw new AlreadyReportedException(getIdDb);
        } else {
            users.setId(getIdDb);
        }
        userRepository.save(user);
    }

    public List<Users> showUser() {
        return userRepository.findAll();
    }

    public void removeUser(long id) {
        if (userRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } if(userRepository.findById(id).isEmpty()) {
            throw new NullPointerException(Constant.noList);
        } else {
            userRepository.deleteById(id);
        }
    }


    public AccountUsers findAccountByName(@RequestParam String name) {
        AccountUsers account = new AccountUsers();

        if (accountRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else if (userRepository.findAll().isEmpty()) {
            throw new NullPointerException(Constant.emptyList);
        } else if(accountRepository.findAll().size() < userRepository.findAll().size()) {
            throw new NullPointerException(Constant.compareSize);
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
