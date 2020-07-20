package com.eMart.main.repository;

import com.eMart.main.entity.InvoiceBody;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceBodyRepositry extends JpaRepository<InvoiceBody,Integer> {
    Iterable<InvoiceBody> findAllByInvoiceId(int i);
}
