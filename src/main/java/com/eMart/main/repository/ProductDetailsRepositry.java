package com.eMart.main.repository;

import com.eMart.main.entity.Product;
import com.eMart.main.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import java.util.List;

@Repository
public interface ProductDetailsRepositry extends JpaRepository<ProductDetails,Integer> {
    @Query(value = "select  p from Product p inner join  ProductDetails pd on p=pd.product and  pd.expiryDate<'2020-08-01 00:00:00.000000'",nativeQuery = false)
    Join<Product,ProductDetails> getExpiredProducts();;
}
