package com.example.techthink.controller.model;

import java.util.List;

public class CourseResponse {
    public Integer id;
    public String name;
    public double price;
    public List<SubCategoryResponse> subCategoryResponses;

    public CourseResponse() {
    }

    public CourseResponse(Integer id, String name, double price, List<SubCategoryResponse> subCategoryResponses) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.subCategoryResponses = subCategoryResponses;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<SubCategoryResponse> getSubCategoryResponses() {
        return subCategoryResponses;
    }

    public void setSubCategoryResponses(List<SubCategoryResponse> subCategoryResponses) {
        this.subCategoryResponses = subCategoryResponses;
    }
}
