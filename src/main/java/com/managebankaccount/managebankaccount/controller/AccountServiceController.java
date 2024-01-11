package com.managebankaccount.managebankaccount.controller;

import com.managebankaccount.managebankaccount.DTO.AccountDto;
import com.managebankaccount.managebankaccount.beans.AccountUsers;
import com.managebankaccount.managebankaccount.beans.Users;
import com.managebankaccount.managebankaccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountServiceController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/course")
    public List<AccountDto> getNew() {
         return accountService.showAllAccount();
    }
    @PostMapping("/course")
    public void postNew(@RequestBody AccountDto account) {
        accountService.addAccount(account);
    }
    @PutMapping("/course/{idAccount}")
    public AccountDto putNew(@PathVariable int idAccount, @RequestBody AccountDto account) {

        return accountService.updateAccount(idAccount,account);
    }
    //
    @DeleteMapping("/course/{idAccount}")
    public void deleteList(@PathVariable long idAccount) {
        accountService.removeAccount(idAccount);
    }

    //
    @GetMapping("/course/deposit/{idAccount}")
    public AccountDto changeDeposit(@PathVariable int idAccount,
                                            @RequestParam(value = "deposit", required = true) double deposit
                                            ,AccountDto account) {
        return accountService.depositMoney(idAccount, account, deposit);
    }
    @GetMapping("/course/withDraw/{idAccount}")
    public AccountDto changeWithDraw(@PathVariable int idAccount,
                                           AccountDto account,
                                             @RequestParam(value = "withDraw") double withDraw) {
        return accountService.withDrawMoney(account, idAccount, withDraw);
    }
    //
    @GetMapping("/course/name")
    public AccountUsers findName(@RequestParam(value = "name") String name) {
         return accountService.findAccountByName(name);
    }
    @GetMapping("/course/birthday")
    public AccountUsers findBirthday(@RequestParam(value = "birthday") String birthday) {
        return accountService.findAccountByBirthday(birthday);
    }
    @PostMapping("/course/user")
    public void postUser(@RequestBody Users user) {
        accountService.addUsers(user);
    }
        @DeleteMapping("/course/user/{id}")
    public void deleteUser(@PathVariable int id) {
        accountService.removeUser(id);
    }
    @GetMapping("/course/user")
    public List<Users> getNewUser() {
        return accountService.showUser();
    }
}
