package com.eMart.main.repository;

import com.eMart.main.model.SupplierList;
import com.eMart.main.entity.Supplier;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EntityManager;
import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepositry extends JpaRepository<Supplier,Integer> {
    Object findBysupplierid(Integer id);



   // @Query(value = "SELECT new com.eMart.main.model.SupplierList(s.supplierid,s.companyname) from supplier s",nativeQuery = true)
   // List<SupplierList> findAllCompanyname();

    @Query(value = "select s.supplierid,s.companyname from supplier s",nativeQuery = true)
    List<Object[]> findAllCompanyname();


}
