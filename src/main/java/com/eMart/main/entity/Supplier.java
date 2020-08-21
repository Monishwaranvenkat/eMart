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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="email"),@UniqueConstraint(columnNames="phone")})
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid",nullable = false)
    int supplierid;
    @Column(name = "companyname",nullable = false)
    String companyname;
    @Column(name = "repname",nullable = false)
    String repname;
    @Column(name = "address",nullable = false)
    String address ;
    @Column(name = "phone",nullable = false)
    long phone ;
    @Column(name = "email",nullable = false)
    String email;


}
