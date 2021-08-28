package com.example.techthink.service;

import com.example.techthink.persistence.Category;
import com.example.techthink.service.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {

    Category create(CategoryDTO request);
    Category readById(Integer id);
    List<Category> readAll();
    Category update(Integer id, CategoryDTO request);
    Boolean delete(Integer id);

}
