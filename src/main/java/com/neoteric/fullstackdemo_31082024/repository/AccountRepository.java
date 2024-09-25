package com.neoteric.fullstackdemo_31082024.repository;

import com.neoteric.fullstackdemo_31082024.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,String> {


     @Query("select a from AccountEntity a where a.accountnumber=:accountnumber and a.pan=:pan")
     AccountEntity getAccountEntity(@Param("accountnumber") String accountnumber,
                                    @Param("pan") String pan);

     AccountEntity findByAccountnumberAndPan(@Param("accountnumber") String accountnumber,
                                             @Param("pan") String pan);


     List<AccountEntity> findByBalanceLessThan(@Param("balance") double balance);

     List<AccountEntity> findByBalanceGreaterThan(@Param("balance") double balance);

     List<AccountEntity> findByBalanceBetween(double lowrange, double upperrange);

}
