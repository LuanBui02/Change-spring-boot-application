package com.managebankaccount.managebankaccount.repository;
import com.managebankaccount.managebankaccount.beans.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
