package com.eMart.main.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
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

    private String hashCode;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<InvoiceBody> invoiceBodies;


}
