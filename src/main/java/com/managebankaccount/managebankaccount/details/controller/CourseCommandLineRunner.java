package com.managebankaccount.managebankaccount.details.controller;

import com.managebankaccount.managebankaccount.details.beans.AccountUsers;
import com.managebankaccount.managebankaccount.details.beans.User;
import com.managebankaccount.managebankaccount.details.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseCommandLineRunner {
    @Autowired
    private AccountService accountService;
    @GetMapping("/course")
    public List<AccountUsers> getNew() {
         return accountService.showAllAccount();
    }
    @GetMapping("/course/user")
    public List<User> getNewUser() {
        return accountService.showUser();
    }
    @PostMapping("/course")
    public AccountUsers postNew(@RequestBody AccountUsers account) {
          return accountService.addAccount(account);
    }
    @PostMapping("/course/user")
    public void postUser(@RequestBody User user) {
        accountService.addUsers(user);
    }
    @PutMapping("/course/{idAccount}")
    public List<AccountUsers> putNew(@PathVariable int idAccount, @RequestBody AccountUsers accountUsers) {
        return accountService.updateAccount(idAccount, accountUsers);
    }
    @DeleteMapping("/course/{idAccount}")
    public void deleteList(@PathVariable long idAccount) {
        accountService.removeAccount(idAccount);
    }
    @DeleteMapping("/course/user/{id}")
    public void deleteUser(@PathVariable int id) {
        accountService.removeUser(id);
    }

    @GetMapping("/course/deposit/{idAccount}")
    public List<AccountUsers> changeDeposit(@PathVariable int idAccount,
                                            @RequestParam(value = "deposit", required = true) double deposit
                                            ,AccountUsers account) {
        return accountService.depositMoney(idAccount, account, deposit);
    }
    @GetMapping("/course/withDraw/{idAccount}")
    public List<AccountUsers> changeWithDraw(@PathVariable int idAccount,
                                             AccountUsers account,
                                             @RequestParam(value = "withDraw") double withDraw) {
        return accountService.withDrawMoney(account, idAccount, withDraw);
    }
    @GetMapping("/course/name")
    public AccountUsers findName(@RequestParam(value = "name") String name) {
         return accountService.findAccountByName(name);
    }
    @GetMapping("/course/birthday")
    public AccountUsers findBirthday(@RequestParam(value = "birthday") String birthday) {
        return accountService.findAccountByBirthday(birthday);
    }

}
