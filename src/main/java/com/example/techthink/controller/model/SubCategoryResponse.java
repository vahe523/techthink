package com.example.techthink.controller.model;

public class SubCategoryResponse {
    private Integer id;
    private String name;
    private CategoryResponse category;

    public SubCategoryResponse() {
    }

    public SubCategoryResponse(Integer id, String name, CategoryResponse category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }
}
