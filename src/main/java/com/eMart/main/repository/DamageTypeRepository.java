package com.eMart.main.repository;

import com.eMart.main.entity.DamageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageTypeRepository extends JpaRepository<DamageType,Integer> {
}
