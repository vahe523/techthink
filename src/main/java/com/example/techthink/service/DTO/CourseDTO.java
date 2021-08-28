package com.example.techthink.service.DTO;


import java.util.List;

public class CourseDTO {
    public String name;
    public double price;
    public List<Integer> subCategoryIds;

    public CourseDTO() {
    }

    public CourseDTO(String name, double price, List<Integer> subCategoryIds) {
        this.name = name;
        this.price = price;
        this.subCategoryIds = subCategoryIds;
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

    public List<Integer> getSubCategoryIds() {
        return subCategoryIds;
    }

    public void setSubCategoryIds(List<Integer> subCategoryIds) {
        this.subCategoryIds = subCategoryIds;
    }
}
