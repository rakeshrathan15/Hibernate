package com.neoteric.fullstackdemo_31082024.controller;


import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.model.Account;
import com.neoteric.fullstackdemo_31082024.service.AccountService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
public class AccountController {


    @PostMapping(value="/api/createAccount/jdbc",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountNumber(@RequestBody Account account)
            throws AccountCreationFailedException{

        AccountService accountService = new AccountService();
        String accountNumber =accountService.createAccount(account);
        account.setAccountNumber(accountNumber);
        return  account;

    }




    @PostMapping(value="/api/createAccount/singleTable",
      consumes = "application/json",
    produces = "application/json")
    public Account getAccountNumberfromHibernateSingleTable(@RequestBody Account account)
            throws AccountCreationFailedException{

        AccountService accountService = new AccountService();
        String accountNumber =accountService.createAccountUsingHibernate(account);
        account.setAccountNumber(accountNumber);
        return  account;

    }


    @PostMapping(value="/api/createAccount/hibernate",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountNumberfromHibernate(@RequestBody Account account)
            throws AccountCreationFailedException{

        AccountService accountService = new AccountService();
        String accountNumber =accountService.oneToManyUsingHibernate(account);
        account.setAccountNumber(accountNumber);
        return  account;

    }

    @PostMapping(value="/api/createAccount",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountNumberHibernatefromUI(@RequestBody Account account)
            throws AccountCreationFailedException{

        AccountService accountService = new AccountService();
        String accountNumber =accountService.oneToManyUsingHibernateFromUI(account);
        account.setAccountNumber(accountNumber);
        return  account;

    }

//    @GetMapping(value = "api/searchAccount",
//    consumes = "application/json",
//    produces = "application/json")
//
//    public Account searchAccount(@RequestHeader("accountinput")
//                                 String accountNumber){
//        AccountService accountService= new AccountService();
//        return accountService.searchAccount(accountNumber);
//    }

    @GetMapping(value = "api/searchAccount/criteria",
            consumes = "application/json",
            produces = "application/json")

    public Account searchAccountcriteria(@RequestHeader("accountinput")
                                 String accountNumber){
        AccountService accountService= new AccountService();
        return accountService.searchAccountCriteria(accountNumber);
    }


    @GetMapping(value ="api/searchAccount",
     consumes = "application/json",
            produces = "application/json")
    public Account searchAccountJPA(@RequestHeader("accountinput")
                                         String accountNumber){


        AccountService accountService= new AccountService();
        return accountService.searchAccountByJPA(accountNumber);
    }

}
