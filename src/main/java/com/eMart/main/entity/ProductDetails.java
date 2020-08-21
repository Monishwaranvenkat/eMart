package com.eMart.main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ProductDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productDetailId;
    @Column(name = "cost")
    Double cost;
    @Column(name = "currency")
    String currency;
    @Column(name = "expiry_date")
    Date expiryDate;
    @Column(name = "location")
    String location;
    @Column(name = "count")
    Integer count;
    @Column(name = "lot_number")
    Long lotNumber;
    Integer supplierCode;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    Product product;


}
