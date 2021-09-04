package com.example.techthink.persistence.repository;

import com.example.techthink.persistence.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
}
