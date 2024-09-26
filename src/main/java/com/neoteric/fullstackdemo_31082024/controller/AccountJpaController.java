package com.neoteric.fullstackdemo_31082024.controller;

import com.neoteric.fullstackdemo_31082024.model.Account;
import com.neoteric.fullstackdemo_31082024.model.AccountEntity;
import com.neoteric.fullstackdemo_31082024.service.AccountServiceWithJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
public class AccountJpaController {

    @Autowired
    AccountServiceWithJpa accountServiceWithSpringJpa;



    @GetMapping(value = "/api/searchAccount/AddressStatusJpql",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountAddressStatus(@RequestHeader("accountinput")
                                     String accountnumber,  @RequestHeader("statusinput")Integer status) {
        return accountServiceWithSpringJpa.searchAccountByAddressStatusJPQl(accountnumber,status);
    }


    @GetMapping(value = "/api/searchAccount/AccountAddressJpql",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountAddress(@RequestHeader("accountinput")
                                                 String accountnumber) {
        return accountServiceWithSpringJpa.searchAccountByAccountAddressJPQL(accountnumber);
    }



    @GetMapping(value = "/api/searchAccount/balance",
            consumes = "application/json",
            produces = "application/json")
    public List<AccountEntity> getAccountBalance(@RequestParam("balanceinput") double balance) {
        return accountServiceWithSpringJpa.accountLessThanBalance(balance);
    }



    @GetMapping(value = "/api/searchAccount/Jpql",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountByAccountAndPanJPQL(@RequestHeader("accountinput")
                                             String accountnumber, @RequestHeader("paninput") String pan) {
        return accountServiceWithSpringJpa.searchAccountByAccountAndPanJPQL(accountnumber, pan);
    }

    @GetMapping(value = "/api/searchAccount/Datajpa",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountByAccountAndPan(@RequestHeader("accountinput")
                                             String accountnumber, @RequestHeader("paninput") String pan) {
        return accountServiceWithSpringJpa.searchAccountByAccountAndPan(accountnumber, pan);
    }
//
//    @GetMapping(value = "/api/searchAccount/balance",
//            consumes = "application/json",
//            produces = "application/json")
//    public List<AccountEntity> searchByBalance(@RequestHeader("balanceinput") double balance){
//        return accountServiceWithSpringJpa.accountLessThanBalance(balance);
//    }

    @GetMapping(value = "/api/searchAccount/balanceGreater",
            consumes = "application/json",
            produces = "application/json")
    public List<AccountEntity> searchByBalanceGreater(@RequestHeader("balanceinput") double balance){
        return accountServiceWithSpringJpa.accountGreaterThanBalance(balance);
    }

    @GetMapping(value = "/api/searchAccount/balanceRange",
            consumes = "application/json",
            produces = "application/json")
    public List<AccountEntity> searchByBalanceRange(@RequestHeader("lowerRangeinput") double lowerRange, @RequestHeader("upperRangeinput") double upperRange){
        return accountServiceWithSpringJpa.accountBetweenBalance(lowerRange, upperRange);
    }


//    @GetMapping(value = "/api/searchAccount/balanceNull")
//    public List<AccountEntity> getAccountsWithBalanceNull(){
//        return accountServiceWithSpringJpa.findAccountsWithNullBalance();
//    }
//
//    @GetMapping(value = "/api/searchAccount/balanceNotNull")
//    public List<AccountEntity> getAccountsWithBalanceNotNull(){
//        return accountServiceWithSpringJpa.findAccountsWithBalanceNotNull();
//    }
//
//    @GetMapping(value = "/api/searchAccount/distnctBalance")
//    public List<AccountEntity> getDistinctAccountsByBalance(@RequestHeader("balanceinput") double balance){
//        return accountServiceWithSpringJpa.findDistinctAccountsByBalance(balance);
//    }

}
