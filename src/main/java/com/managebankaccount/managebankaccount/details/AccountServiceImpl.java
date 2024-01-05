package com.managebankaccount.managebankaccount.details;

import com.managebankaccount.managebankaccount.details.beans.AccountUsers;
import com.managebankaccount.managebankaccount.details.beans.User;
import com.managebankaccount.managebankaccount.details.service.AccountService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.function.Function;
@Service
public class AccountServiceImpl implements AccountService{

    List<AccountUsers> accountUsers = new ArrayList<>();

    AccountUsers account1 = new AccountUsers(1, 123456, "01", "123Luan", 2000);
    List<User> users = new ArrayList<>();
    @Override
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
        if(accountUsers.isEmpty()) {
            throw new RuntimeException(Constant.emptyList);
        } else {
                String password = account.getPassword();
                if(password.trim().isEmpty()) {
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
        if(accountUsers.isEmpty()) {
            throw new RuntimeException(Constant.emptyList);
        } else {
            for( int i = 0; i < accountUsers.size(); i++) {
                if(idAccount == accountUsers.get(i).getIdAccount()) {
                    accountUsers.remove(accountUsers.get(i));
                }
            }
        }
    }
    public void removeUser(int id) {
        if(users.isEmpty()) {
            throw new RuntimeException(Constant.emptyList);
        } else {
            for( int i = 0; i < users.size(); i++) {
                if(id == users.get(i).getId()) {
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
                        throw new RuntimeException(Constant.conditionWithDraw) ;
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
                }
            }
        }
        return account;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends AccountUsers> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends AccountUsers> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<AccountUsers> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public AccountUsers getOne(Long aLong) {
        return null;
    }

    @Override
    public AccountUsers getById(Long aLong) {
        return null;
    }

    @Override
    public AccountUsers getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends AccountUsers> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends AccountUsers> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends AccountUsers> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends AccountUsers> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends AccountUsers> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends AccountUsers> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends AccountUsers, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends AccountUsers> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AccountUsers> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AccountUsers> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<AccountUsers> findAll() {
        return null;
    }

    @Override
    public List<AccountUsers> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(AccountUsers entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AccountUsers> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<AccountUsers> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<AccountUsers> findAll(Pageable pageable) {
        return null;
    }
}
