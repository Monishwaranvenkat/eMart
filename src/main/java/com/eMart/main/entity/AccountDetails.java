package com.eMart.main.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_detail")
public class AccountDetails {

    @Id
    @Column(name = "user_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "password",nullable = false)
    @NonNull
    private String password;

    @Column(name="isactive",nullable = false)
    @NotNull
    private int isactive=1;

    @Column(name = "islocked",nullable = false)
    @NotNull
    private int islocked=1;

    @Column
    @NotNull
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    EmployeeDetails employeeDetails;



}
