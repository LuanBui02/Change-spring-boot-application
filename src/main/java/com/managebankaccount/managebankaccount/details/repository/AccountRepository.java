package com.managebankaccount.managebankaccount.details.repository;

import com.managebankaccount.managebankaccount.details.beans.AccountUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountUsers, Long> {
}
