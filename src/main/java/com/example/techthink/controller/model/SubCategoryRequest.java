package com.example.techthink.controller.model;

public class SubCategoryRequest {
    private String name;
    private Integer categoryId;

    public SubCategoryRequest() {
    }

    public SubCategoryRequest(String name, Integer categoryId) {
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
