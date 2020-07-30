package com.eMart.main.repository;

import com.eMart.main.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepositry extends JpaRepository<Invoice,Integer> {

}
