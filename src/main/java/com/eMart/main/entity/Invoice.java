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
    private Integer id;
    @Column(name = "No_products")
   // @NotNull
    private Integer numberOfProduct;
    @Column(name = "total_amount")
   // @NotNull
    private Integer  totalAmount;
    @Column(name = "timestamp")
   // @NotNull
    private Timestamp timeStamp;

    @Column(name = "hashCode")
    private String hashCode;

    @Column(name="supplierid")
    private Integer supplierid;

    @Column(name="status")
    private Boolean status=false;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<InvoiceSummary> invoiceSummaries=null;

    public Invoice(Integer id, Integer numberOfProduct, Integer totalAmount, Timestamp timeStamp, Integer supplierid, Boolean status) {
        this.id = id;
        this.numberOfProduct = numberOfProduct;
        this.totalAmount = totalAmount;
        this.timeStamp = timeStamp;
        this.supplierid = supplierid;
        this.status = status;
    }
}
