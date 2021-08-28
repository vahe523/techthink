package com.example.techthink.controller.model;

public class CategoryRequest {
    public String name;

    public CategoryRequest(String name) {
        this.name = name;
    }

    public CategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
