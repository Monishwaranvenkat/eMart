package com.eMart.main.repository;

import com.eMart.main.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface InvoiceRepositry extends JpaRepository<Invoice,Integer> {

   @Query(value = "select invoice_id,no_products,total_amount,timestamp,supplierid,status from Invoice",nativeQuery = true)
   List<Object[]> getMyInvoice();

   @Query(value = "select * from Invoice where status= 0",nativeQuery = true)
   Optional<Invoice[]> getNonVerifiedInvoice();


   Optional<Invoice> findById(int id);

   @Query(value = "select count(*) from invoice i where i.hash_code=:hash_code and i.timestamp=:timestamp and i.supplierid=:supplierid ",nativeQuery = true)
   Integer isInvoiceExist(@Param("hash_code") String hash_code,@Param("timestamp") String timestamp,@Param("supplierid") Integer supplierid);
  // @Query(value = "select count(*) from invoice i where i.timestamp=:timestamp",nativeQuery = true)
   //Integer isTimeStampExist(@Param("timestamp") String timestamp);

}
