package com.eMart.main.repository;

import com.eMart.main.entity.Invoice;
import com.eMart.main.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepositry extends JpaRepository<Supplier,Integer> {
    Object findBysupplierID(Integer id);

    @Query(value = "select s.supplierid,s.companyname from supplier s",nativeQuery = true)
    List<Object[]> findAllCompanyname();
}
