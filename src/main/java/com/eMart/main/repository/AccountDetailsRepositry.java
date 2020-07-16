package com.eMart.main.repository;

import com.eMart.main.models.AccountDetails;
import com.eMart.main.models.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDetailsRepositry extends JpaRepository<AccountDetails,Integer> {
    Optional<AccountDetails> findByEmployeeDetailsEmail(String email);

}