package com.example.techthink.service.DTO;

public class CategoryDTO {
    public String name;

    public CategoryDTO(String name) {
        this.name = name;
    }

    public CategoryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
