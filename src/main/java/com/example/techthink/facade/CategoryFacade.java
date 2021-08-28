package com.example.techthink.facade;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.model.CategoryRequest;
import com.example.techthink.controller.model.CategoryResponse;
import com.example.techthink.converter.Converter;
import com.example.techthink.persistence.Category;
import com.example.techthink.service.CategoryService;
import com.example.techthink.service.DTO.CategoryDTO;

import java.util.List;

@Facade
public class CategoryFacade {

    private final CategoryService categoryService;
    private final Converter converter;

    public CategoryFacade(CategoryService categoryService, Converter converter) {
        this.categoryService = categoryService;
        this.converter = converter;
    }

    public CategoryResponse create(CategoryRequest request){
        CategoryDTO categoryDTO = convertToDTO(request);
        Category category = categoryService.create(categoryDTO);
        CategoryResponse categoryResponse = converter.fromCategoryToResponse(category);
        return categoryResponse;
    }

    public CategoryResponse readById(Integer id){
        Category category = categoryService.readById(id);
        CategoryResponse categoryResponse = converter.fromCategoryToResponse(category);
        return categoryResponse;
    }

    public List<CategoryResponse> readAll(){
        List<Category> categories = categoryService.readAll();
        List<CategoryResponse> categoryResponses = converter.fromCategoryToResponseList(categories);
        return categoryResponses;
    }

    public CategoryResponse update(Integer id, CategoryRequest request){
        CategoryDTO categoryDTO = convertToDTO(request);
        Category update = categoryService.update(id, categoryDTO);
        CategoryResponse categoryResponse = converter.fromCategoryToResponse(update);
        return categoryResponse;
    }

    public Boolean delete(Integer id){
        Boolean delete = categoryService.delete(id);
        return delete;
    }

    private CategoryDTO convertToDTO(CategoryRequest request){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(request.getName());
        return categoryDTO;
    }
}
