package com.neoteric.fullstackdemo_31082024.service;

import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.hibernate.HibernateUtils;

import com.neoteric.fullstackdemo_31082024.model.*;
import com.neoteric.fullstackdemo_31082024.model.AccountAddressEntity;
import com.neoteric.fullstackdemo_31082024.model.AccountEntity;
import com.neoteric.fullstackdemo_31082024.model.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class AccountService {


    public Account searchAccountByJPA(String accountNumber){

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("jpaDemo");
        EntityManager entityManager=emf.createEntityManager();
        entityManager.getTransaction().begin();
        jakarta.persistence.Query query=entityManager.createQuery("Select a from AccountEntity a where a.accountNumber=:inputAccountNumber");
        query.setParameter("inputAccountNumber",accountNumber);

        List<AccountEntity> accountEntities=query.getResultList();
        AccountEntity accountEntity=accountEntities.get(0);

        Account account= Account.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .mobileNumber(accountEntity.getMobileNumber())
                .balance(accountEntity.getBalance())
                .pan(accountEntity.getPan()).build();



        List<AccountAddressEntity> accountAddressEntityList=
                accountEntity.getAccountAddressEntityList();

        if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size()>0){
            AccountAddressEntity accountAddressEntity=accountAddressEntityList.get(0);
            Address address= new Address();
            address.setAdd1(accountAddressEntity.getAddress1());
            address.setAdd2(accountAddressEntity.getAddress2());
            address.setCity(accountAddressEntity.getCity());
            address.setPincode(accountAddressEntity.getPincode());
            address.setState(accountAddressEntity.getState());
            account.setAddress(address);
        }
        entityManager.getTransaction().commit();
        return account;

    }




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






    public Account searchAccount(String accounNumber){
        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session= sessionFactory.openSession();
        Query<AccountEntity> query=session.createQuery("From AccountEntity a where a.accountNumber=:inputAccountNumber");
        query.setParameter("inputAccountNumber",accounNumber);
        AccountEntity accountEntity= query.list().get(0);

        Account account= Account.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .mobileNumber(accountEntity.getMobileNumber())
                .balance(accountEntity.getBalance())
                .pan(accountEntity.getPan())
                .address(
                        Address.builder()
                                .add1(accountEntity.getAccountAddressEntityList().get(0).getAddress1())
                                .add2(accountEntity.getAccountAddressEntityList().get(0).getAddress2())

                                .build()
                ).build();

        return account;

    }



// Other imports...

    public Account searchAccountCriteria(String accountNumber) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Account account = null;

        try {
            // Begin transaction
            session.beginTransaction();

            // Create CriteriaBuilder
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // Create CriteriaQuery
            CriteriaQuery<AccountEntity> cq = cb.createQuery(AccountEntity.class);

            // Define the root of the query (FROM AccountEntity)
            Root<AccountEntity> root = cq.from(AccountEntity.class);

            // Add restriction (WHERE accountNumber = :accountNumber)
            cq.select(root).where(cb.equal(root.get("accountNumber"), accountNumber));

            // Execute the query
            Query<AccountEntity> query = session.createQuery(cq);
            AccountEntity accountEntity = query.getSingleResult();

            // Commit transaction
            session.getTransaction().commit();

            // Map AccountEntity to Account DTO
            if (accountEntity != null) {
                account = Account.builder()
                        .accountNumber(accountEntity.getAccountNumber())
                        .mobileNumber(accountEntity.getMobileNumber())
                        .balance(accountEntity.getBalance())
                        .pan(accountEntity.getPan())
                        .address(
                                Address.builder()
                                        .add1(accountEntity.getAccountAddressEntityList().get(0).getAddress1())
                                        .add2(accountEntity.getAccountAddressEntityList().get(0).getAddress2())
                                        .build()
                        ).build();
            }

        } catch (Exception e) {
            // Rollback transaction if something goes wrong
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return account;
    }



}
