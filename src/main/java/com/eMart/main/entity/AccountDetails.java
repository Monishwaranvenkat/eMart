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
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name="isactive")
    @NotNull
    private int isactive;

    @Column(name = "islocked")
    @NotNull
    private int islocked;

    @Column
    @NotNull
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    EmployeeDetails employeeDetails;



}
