package com.eMart.main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoice_summary")

public class InvoiceSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int id;
    @Column(name="vendor_code")
    private int vendorCode;
    @Column(name = "product_Description")
    private String productDescription;
    @Column(name="product_Category")
    private String productCategory;
    @Column(name = "count")
    private int count;
    @Column(name = "cost")
    private double cost;
    @Column(name = "currency")
    private String currency;
    @Column(name = "expiry_date")
    private Date expiryDate;
    @Column(name="status")
    String status;
    @Column(name = "return_reason")
    String returnReason;
   // @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Invoice invoice;

}
