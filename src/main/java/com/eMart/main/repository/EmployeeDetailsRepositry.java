package com.eMart.main.repository;

import com.eMart.main.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDetailsRepositry extends JpaRepository<EmployeeDetails,Integer> {
    Optional<EmployeeDetails> findByEmail(String email);
    @Query(value = "select count(*) from employee_details e where e.email=:email",nativeQuery = true)
    Integer isEmailExist(@Param("email") String email);
}