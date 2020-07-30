package com.eMart.main.repository;

import com.eMart.main.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface InvoiceRepositry extends JpaRepository<Invoice,Integer> {

   @Query(value = "select invoice_id,timestamp,total_amount from Invoice",nativeQuery = true)
   List<Object[]> getMyInvoice();
   Optional<Invoice> findById(int id);
   @Query(value = "select count(*) from invoice i where i.hash_code=:hash_code ",nativeQuery = true)
   Integer isHashExist(@Param("hash_code") String hash_code);
   @Query(value = "select count(*) from invoice i where i.timestamp=:timestamp",nativeQuery = true)
   Integer isTimeStampExist(@Param("timestamp") String timestamp);

}
