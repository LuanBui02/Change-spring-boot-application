package com.managebankaccount.managebankaccount.details.repository;
import com.managebankaccount.managebankaccount.details.beans.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
