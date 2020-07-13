package com.eMart.main.repository;

import com.eMart.main.models.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeDetailsRepositry extends JpaRepository<EmployeeDetails,Integer> {
    Optional<EmployeeDetails> findByEmail(String email);
}
