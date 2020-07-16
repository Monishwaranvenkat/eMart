package com.eMart.main.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "invoice_body")

public class InvoiceBody {
    @Id
    @Column(name="product_id")
    private int id;
    @Column(name="vendor_code")
    private int vendorCode;
    @Column(name="product_Category")
    private String productCategory;
    @Column(name = "product_Description")
    private String productDescription;
    @Column(name = "count")
    private int count;
    @Column(name = "cost")
    private double cost;
    @Column(name = "currency")
    private double currency;
    @Column(name = "expiry_date")
    private Date expiryDate;
   // @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Invoice invoice;

}
