package com.example.techthink.service.DTO;

public class SubCategoryDTO {
    private String name;
    private Integer categoryId;

    public SubCategoryDTO() {
    }

    public SubCategoryDTO(String name, Integer categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
