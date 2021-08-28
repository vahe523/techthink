package com.example.techthink.service.DTO;

import java.util.Date;

public class CourseSectionDTO {
    private Long userId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int capacity;
    private String photoURL;
    private Integer courseId;
    private Integer formatId;
    private Integer addressId;

    public CourseSectionDTO() {
    }

    public CourseSectionDTO(Long userId, String name, String description, Date startDate, Date endDate, int capacity, String photoURL, Integer courseId, Integer formatId, Integer addressId) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.photoURL = photoURL;
        this.courseId = courseId;
        this.formatId = formatId;
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
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
