package com.neoteric.fullstackdemo_31082024.service;

import com.neoteric.fullstackdemo_31082024.model.Account;
import com.neoteric.fullstackdemo_31082024.model.AccountAddressEntity;
import com.neoteric.fullstackdemo_31082024.model.AccountEntity;
import com.neoteric.fullstackdemo_31082024.model.Address;
import com.neoteric.fullstackdemo_31082024.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceWithJpa {


    @Autowired
    AccountRepository accountRepositary2;

    public List<AccountEntity> accountLessThanBalance(double balance){
        return accountRepositary2.findByBalanceLessThan(balance);
    }

    public List<AccountEntity> accountGreaterThanBalance(double balance){
        return accountRepositary2.findByBalanceGreaterThan(balance);
    }


    public List<AccountEntity> accountBetweenBalance(double lowerRange, double upperRange) {
        return accountRepositary2.findByBalanceBetween(lowerRange, upperRange);
    }

//    public List<AccountEntity> findAccountsWithNullBalance(){
//        return accountRepositary2.findByBalanceIsNull();
//    }
//
//    public List<AccountEntity> findAccountsWithBalanceNotNull(){
//        return accountRepositary2.findByBalanceIsNotNull();
//    }
//
//    public List<AccountEntity> findDistinctAccountsByBalance(double balance){
//        return accountRepositary2.findByDistinctByBalance(balance);
//    }

    public Account searchAccountByAccountAndPan(String accountnumber, String pan){
        Account account=null;
        AccountEntity accountEntity =accountRepositary2.findByAccountnumberAndPan(accountnumber,pan);
        if(accountEntity!= null){

            account=Account.builder()
                    .accountNumber(accountEntity.getAccountnumber())
                    .mobileNumber(accountEntity.getMobileNumber())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .name(accountEntity.getName()).build();
            List<AccountAddressEntity> accountAddressEntityList =
                    accountEntity.getAccountAddressEntityList();
            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
                Address address = new Address();
                address.setAdd1(accountAddressEntity.getAddress1());
                address.setAdd2(accountAddressEntity.getAddress2());
                address.setCity(accountAddressEntity.getCity());
                address.setPincode(accountAddressEntity.getPincode());
                address.setState(accountAddressEntity.getState());

                account.setAddress(address);
            }
        }
        return account;
    }


    public Account searchAccountByAccountAndPanJPQL(String accountnumber, String pan){
        Account account=null;
        AccountEntity accountEntity =accountRepositary2.getAccountEntity(accountnumber,pan);
        if(accountEntity!= null){

            account=Account.builder()
                    .accountNumber(accountEntity.getAccountnumber())
                    .mobileNumber(accountEntity.getMobileNumber())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .name(accountEntity.getName()).build();
            List<AccountAddressEntity> accountAddressEntityList =
                    accountEntity.getAccountAddressEntityList();
            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
                Address address = new Address();
                address.setAdd1(accountAddressEntity.getAddress1());
                address.setAdd2(accountAddressEntity.getAddress2());
                address.setCity(accountAddressEntity.getCity());
                address.setPincode(accountAddressEntity.getPincode());
                address.setState(accountAddressEntity.getState());

                account.setAddress(address);
            }
        }
        return account;
    }



    public Account searchAccountByAccountAddressJPQL(String accountnumber){
        Account account=null;
        AccountEntity accountEntity =accountRepositary2.getAccountEntityAddress(accountnumber);
        if(accountEntity!= null){

            account=Account.builder()
                    .accountNumber(accountEntity.getAccountnumber())
                    .mobileNumber(accountEntity.getMobileNumber())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .name(accountEntity.getName()).build();
            List<AccountAddressEntity> accountAddressEntityList =
                    accountEntity.getAccountAddressEntityList();
            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
                Address address = new Address();
                address.setAdd1(accountAddressEntity.getAddress1());
                address.setAdd2(accountAddressEntity.getAddress2());
                address.setCity(accountAddressEntity.getCity());
                address.setPincode(accountAddressEntity.getPincode());
                address.setState(accountAddressEntity.getState());

                account.setAddress(address);
            }
        }
        return account;
    }








    public Account searchAccountByAddressJPQl(String accountnumber){

        Account account=null;
        AccountEntity accountEntity =accountRepositary2.getAccountEntityAddress(accountnumber);
        if(accountEntity!= null){

            account=Account.builder()
                    .accountNumber(accountEntity.getAccountnumber())
                    .mobileNumber(accountEntity.getMobileNumber())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .name(accountEntity.getName()).build();
            List<AccountAddressEntity> accountAddressEntityList =
                    accountEntity.getAccountAddressEntityList();
            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
                Address address = new Address();
                address.setAdd1(accountAddressEntity.getAddress1());
                address.setAdd2(accountAddressEntity.getAddress2());
                address.setCity(accountAddressEntity.getCity());
                address.setPincode(accountAddressEntity.getPincode());
                address.setState(accountAddressEntity.getState());

                account.setAddress(address);


            }
        }
        return account;
    }






    public Account searchAccountByAddressStatusJPQl(String accountnumber,Integer status){

        Account account=null;
        AccountEntity accountEntity =accountRepositary2.getAccountEntityAddressstatus(accountnumber,status);
        if(accountEntity!= null){

            account=Account.builder()
                    .accountNumber(accountEntity.getAccountnumber())
                    .mobileNumber(accountEntity.getMobileNumber())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .name(accountEntity.getName()).build();
            List<AccountAddressEntity> accountAddressEntityList =
                    accountEntity.getAccountAddressEntityList();
            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
                Address address = new Address();
                address.setAdd1(accountAddressEntity.getAddress1());
                address.setAdd2(accountAddressEntity.getAddress2());
                address.setCity(accountAddressEntity.getCity());
                address.setPincode(accountAddressEntity.getPincode());
                address.setState(accountAddressEntity.getState());

                account.setAddress(address);


            }
        }
        return account;
    }

}
