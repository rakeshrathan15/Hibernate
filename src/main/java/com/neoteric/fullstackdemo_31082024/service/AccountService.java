package com.neoteric.fullstackdemo_31082024.service;

import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.hibernate.HibernateUtils;
import com.neoteric.fullstackdemo_31082024.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService {

    public String oneToManyUsingHibernate(Account account){

        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();

        AdharEntity adharEntity= new AdharEntity();
        adharEntity.setName(account.getName());

        List<AddressEntity> addressEntityList = new ArrayList<>();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setState(account.getMobileNumber());

        addressEntity.setMyMappedByTestEntity(adharEntity);

        addressEntityList.add(addressEntity);
        adharEntity.setAddressEntityList(addressEntityList);

        session.persist(adharEntity);
        transaction.commit();
        return String.valueOf(adharEntity.adharNumber);
    }

    public String oneToManyUsingHibernateFromUI(Account account){

        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();

        AccountEntity accountEntity= new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNumber(account.getMobileNumber());




        List<AccountAddressEntity> accountAddressEntityList = new ArrayList<>();
        AccountAddressEntity accountAddressEntity= new AccountAddressEntity();
        accountAddressEntity.setAddress1(account.getAddress().getAdd1());
        accountAddressEntity.setAddress2(account.getAddress().getAdd2());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setStatus(1);
        accountAddressEntity.setAccountEntity(accountEntity);

        accountAddressEntityList.add(accountAddressEntity);
        accountEntity.setAccountAddressEntityList(accountAddressEntityList);






        session.persist(accountEntity);
        transaction.commit();
        return accountEntity.getAccountNumber();
    }


    public String createAccountUsingHibernate(Account account){

        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();

      AccountEntity accountEntity=new AccountEntity();
      accountEntity.setAccountNumber(UUID.randomUUID().toString());
      accountEntity.setName(account.getName());
      accountEntity.setPan(account.getPan());
      accountEntity.setBalance(account.getBalance());
      accountEntity.setMobileNumber(account.getMobileNumber());
      session.persist(accountEntity);
      transaction.commit();
      return accountEntity.getAccountNumber();
    }








    public String createAccount(Account account) throws AccountCreationFailedException {
                  String accountNumber=null;

        try {

            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
             accountNumber= UUID.randomUUID().toString();


            String query="insert into bank.account values('"+
                    accountNumber+"'"+","+

            "'"+account.getName()+"'"+","+
            "'"+account.getPan()+"'"+","+
            "'"+account.getMobileNumber()+"'"+","
           + account.getBalance()+")";


            //    insert into bank.account values('1234','rakesh','grc12345','7013776567',20000);

          int status=  stmt.executeUpdate(query);
          if(status==1){

              System.out.println("Account is created "+accountNumber);
          }else {
              throw new AccountCreationFailedException("Account creation is failed"+account.getPan());
          }
        }
        catch (SQLException e){
            System.out.println("Exception ocurred in sql"+e);
        }
        catch (AccountCreationFailedException e){
            System.out.println("Exception ocurred"+e);
            throw e;
        }
        return accountNumber;
    }
}
