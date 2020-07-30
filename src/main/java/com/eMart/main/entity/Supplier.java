package com.eMart.main.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Value;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid",nullable = false)
    int supplierID;
    @Column(name = "companyname",nullable = false)
    String companyName;
    @Column(name = "repname",nullable = false)
    String repName;
    @Column(name = "address",nullable = false)
    String address ;
    @Column(name = "phone",nullable = false)
    int phone ;
    @Column(name = "email",nullable = false)
    String email;
}
