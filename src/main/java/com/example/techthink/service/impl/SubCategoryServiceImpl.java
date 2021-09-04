package com.example.techthink.service.impl;

import com.example.techthink.persistence.model.Category;
import com.example.techthink.persistence.model.SubCategory;
import com.example.techthink.persistence.repository.SubCategoryRepository;
import com.example.techthink.service.CategoryService;
import com.example.techthink.service.DTO.SubCategoryDTO;
import com.example.techthink.service.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryService categoryService;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryService categoryService) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public SubCategory create(SubCategoryDTO request) {
        SubCategory build = build(request);
        SubCategory saved = subCategoryRepository.save(build);
        return saved;
    }

    @Override
    public SubCategory readById(Integer id) {
        SubCategory byId = subCategoryRepository.getById(id);
        return byId;
    }

    @Override
    public List<SubCategory> readAll() {
        List<SubCategory> all = subCategoryRepository.findAll();
        return all;

    }

    @Override
    public SubCategory update(Integer id, SubCategoryDTO request) {
        SubCategory byId = subCategoryRepository.getById(id);
        SubCategory subCategory = updateGivenSubCategory(byId, request);
        SubCategory updated = subCategoryRepository.save(subCategory);
        return updated;
    }

    @Override
    public Boolean delete(Integer id) {
        subCategoryRepository.deleteById(id);
        return !subCategoryRepository.existsById(id);
    }

    private SubCategory build(SubCategoryDTO request){
        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.getName());
        subCategory.setCategory(categoryService.readById(request.getCategoryId()));
        return subCategory;
    }

    private SubCategory updateGivenSubCategory(SubCategory subCategory, SubCategoryDTO request){
        subCategory.setName(request.getName());
        Category category = categoryService.readById(request.getCategoryId());
        subCategory.setCategory(category);
        return subCategory;
    }


}
