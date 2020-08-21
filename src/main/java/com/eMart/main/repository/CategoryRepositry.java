package com.eMart.main.repository;

import com.eMart.main.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepositry extends JpaRepository<Category,Integer> {

    Optional<Category> findByCategoryName(String categoryName);
}
