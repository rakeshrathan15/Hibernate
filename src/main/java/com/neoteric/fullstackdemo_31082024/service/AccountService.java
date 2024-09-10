package com.neoteric.fullstackdemo_31082024.service;

import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.model.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class AccountService {

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
