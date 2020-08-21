package com.eMart.main.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DamageType {
    @Id
    @Column(name = "damage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "damage_type")
    String damageType;
}
