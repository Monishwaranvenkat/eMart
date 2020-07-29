package com.eMart.main.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "employee_details",uniqueConstraints = {@UniqueConstraint(columnNames="email")})

public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "name",nullable = false)
    @NotNull
    private String name;

    @Column(name = "email",unique = true,nullable = false)
    @NotNull
    private String email;

    @Column(name = "dob",nullable = false)
    @NotNull
    private String dob;

    @Column(name = "hire_date",nullable = false)
    @NotNull
    private String hiredate;

}
