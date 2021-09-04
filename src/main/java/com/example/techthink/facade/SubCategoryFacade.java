package com.example.techthink.facade;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.model.SubCategoryRequest;
import com.example.techthink.controller.model.SubCategoryResponse;
import com.example.techthink.converter.Converter;
import com.example.techthink.persistence.model.SubCategory;
import com.example.techthink.service.DTO.SubCategoryDTO;
import com.example.techthink.service.SubCategoryService;

import java.util.List;

@Facade
public class SubCategoryFacade {

    private final SubCategoryService service;
    private final Converter converter;

    public SubCategoryFacade(SubCategoryService service, Converter converter) {
        this.service = service;
        this.converter = converter;
    }

    public SubCategoryResponse create(SubCategoryRequest request){
        SubCategoryDTO subCategoryDTO = convertToDTO(request);
        SubCategory subCategory = service.create(subCategoryDTO);
        SubCategoryResponse subCategoryResponse = converter.fromSubCategoryToResponse(subCategory);
        return subCategoryResponse;
    }

    public SubCategoryResponse readById(Integer id){
        SubCategory subCategory = service.readById(id);
        SubCategoryResponse subCategoryResponse = converter.fromSubCategoryToResponse(subCategory);
        return subCategoryResponse;
    }

    public List<SubCategoryResponse> readAll(){
        List<SubCategory> subCategories = service.readAll();
        List<SubCategoryResponse> subCategoryResponses = converter.fromSubCategoryToResponseList(subCategories);
        return subCategoryResponses;
    }
    public SubCategoryResponse update(Integer id, SubCategoryRequest request){
        SubCategoryDTO subCategoryDTO = convertToDTO(request);
        SubCategory update = service.update(id, subCategoryDTO);
        SubCategoryResponse subCategoryResponse = converter.fromSubCategoryToResponse(update);
        return subCategoryResponse;
    }

    public Boolean delete(Integer id){
        Boolean delete = service.delete(id);
        return delete;
    }

    private SubCategoryDTO convertToDTO(SubCategoryRequest request){
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setName(request.getName());
        subCategoryDTO.setCategoryId(request.getCategoryId());
        return subCategoryDTO;

    }
}
