package com.example.techthink.service.impl;

import com.example.techthink.persistence.Category;
import com.example.techthink.persistence.repository.CategoryRepository;
import com.example.techthink.service.CategoryService;
import com.example.techthink.service.DTO.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(CategoryDTO request) {
        Category category = buildCategory(request);
        Category savedCategory = categoryRepository.save(category);
        return savedCategory;
    }


    @Override
    public Category readById(Integer id) {
        Category byId = categoryRepository.getById(id);
        return byId;
    }

    @Override
    public List<Category> readAll() {
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    @Override
    public Category update(Integer id, CategoryDTO request) {
        Category byId = categoryRepository.getById(id);
        Category category = updateGivenCategory(byId, request);
        Category updated = categoryRepository.save(category);
        return updated;
    }

    @Override
    public Boolean delete(Integer id) {
        categoryRepository.deleteById(id);
        return !categoryRepository.existsById(id);
    }

    private Category buildCategory(CategoryDTO request){
        Category category = new Category();
        category.setName(request.getName());
        return category;

    }

    private Category updateGivenCategory(Category category, CategoryDTO request){
        category.setName(request.getName());
        return category;
    }
}
