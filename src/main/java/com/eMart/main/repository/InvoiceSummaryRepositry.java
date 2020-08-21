package com.eMart.main.repository;

import com.eMart.main.entity.InvoiceSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceSummaryRepositry extends JpaRepository<InvoiceSummary,Integer> {
    Iterable<InvoiceSummary> findAllByInvoiceId(int i);
}
