package com.neoteric.fullstackdemo_31082024.repository;

import com.neoteric.fullstackdemo_31082024.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,String> {


     @Query("select a from AccountEntity a left join fetch a.accountAddressEntityList ad where a.accountnumber=ad.accountnumber"
             +" and a.accountnumber=:accountnumber and ad.status=:status")
     AccountEntity getAccountEntityAddressstatus(@Param("accountnumber") String accountnumber,
                                                 @Param("status") Integer status);


     @Query("select a from AccountEntity a inner join  a.accountAddressEntityList ad where a.accountnumber=ad.accountnumber and a.accountnumber=:accountnumber")
     AccountEntity getAccountEntityAddress(@Param("accountnumber") String accountnumber);



     @Query("select a from AccountEntity a where a.accountnumber=:accountnumber and a.pan=:pan")
     AccountEntity getAccountEntity(@Param("accountnumber") String accountnumber,
                                    @Param("pan") String pan);



     @Query("select a from AccountEntity a where a.balance=:balance")
     AccountEntity getAccountEntity2(@Param("balance") String balance);


     AccountEntity findByAccountnumberAndPan(@Param("accountnumber") String accountnumber,
                                             @Param("pan") String pan);


     List<AccountEntity> findByBalanceLessThan(@Param("balance") double balance);

     List<AccountEntity> findByBalanceGreaterThan(@Param("balance") double balance);

     List<AccountEntity> findByBalanceBetween(double lowrange, double upperrange);

}
