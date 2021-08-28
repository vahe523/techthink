package com.example.techthink.controller.model;

import java.time.LocalDate;
import java.util.Date;

public class CourseSectionRequest {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int capacity;
    private Integer courseId;
    private Integer formatId;
    private Integer addressId;

    public CourseSectionRequest() {
    }

    public CourseSectionRequest(String name, String description, Date startDate, Date endDate, int capacity, Integer courseId, Integer formatId, Integer addressId) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.courseId = courseId;
        this.formatId = formatId;
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getFormatId() {
        return formatId;
    }

    public void setFormatId(Integer formatId) {
        this.formatId = formatId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}
