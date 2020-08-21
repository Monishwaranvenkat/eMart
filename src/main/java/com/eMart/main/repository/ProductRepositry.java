package com.eMart.main.repository;


import com.eMart.main.entity.Product;
import com.eMart.main.entity.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.JoinTable;
import javax.persistence.criteria.Join;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositry extends JpaRepository<Product,Integer> {
    @Autowired


    Optional<Product> findByDescription(String description);
    @Query(value = "select CASE  WHEN count(*)> 0 THEN 'true' ELSE 'false' END from product p where p.description=:description",nativeQuery = true)
    Boolean isProductExist(String description);

    @Query(value = "select distinct p from Product p  join fetch p.productDetails pd  where  pd.expiryDate<:expiryDate ",nativeQuery = false)
    List<Product> getExpiredProducts(Date expiryDate);
}
