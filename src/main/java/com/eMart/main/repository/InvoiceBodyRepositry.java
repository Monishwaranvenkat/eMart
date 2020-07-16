package com.eMart.main.repository;

import com.eMart.main.models.InvoiceBody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceBodyRepositry extends JpaRepository<InvoiceBody,Integer> {
    Iterable<InvoiceBody> findAllByInvoiceId(int i);
}
