package com.eMart.main.repository;

import com.eMart.main.entity.Invoice;
import com.eMart.main.model.InvoiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public interface InvoiceRepositry extends JpaRepository<Invoice,Integer> {

   @Query(value = "select invoice_id,timestamp,total_amount from Invoice",nativeQuery = true)
   List<Object[]> getMyInvoice();
   Optional<Invoice> findById(int id);

}
