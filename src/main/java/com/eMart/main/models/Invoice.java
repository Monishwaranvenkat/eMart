package com.eMart.main.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "No_products")
   // @NotNull
    private int numberOfProduct;
    @Column(name = "total_amount")
   // @NotNull
    private int  totalAmount;
    @Column(name = "timestamp")
   // @NotNull
    private Timestamp timeStamp;
   // @Transient
   // private String hashCode;
   //@JsonIgnoreProperties("invoice")
    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<InvoiceBody> invoiceBodies;


}
