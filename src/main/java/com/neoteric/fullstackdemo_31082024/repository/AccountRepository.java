package com.neoteric.fullstackdemo_31082024.repository;

import com.neoteric.fullstackdemo_31082024.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,String> {
}
