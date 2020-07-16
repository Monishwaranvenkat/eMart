package com.eMart.main.repository;

import com.eMart.main.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface InvoiceRepositry extends JpaRepository<Invoice,Integer> {
    @Query(value = "select id,no_products,timestamp,total_amount from Invoice",nativeQuery = true)
    Iterable<Invoice> getInvoice();
}
