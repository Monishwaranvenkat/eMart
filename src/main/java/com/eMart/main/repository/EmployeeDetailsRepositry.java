package com.eMart.main.repository;

import com.eMart.main.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDetailsRepositry extends JpaRepository<EmployeeDetails,Integer> {
    Optional<EmployeeDetails> findByEmail(String email);
}