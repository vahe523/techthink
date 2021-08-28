package com.example.techthink.controller.model;

import java.util.Date;
import java.util.List;

public class CourseSectionResponse {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int capacity;
    private String sectionPhoto;
    private CourseResponse course;
    private String format;
    private AddressResponse address;


    public CourseSectionResponse() {

    }

    public CourseSectionResponse(Long id, String name, String description, Date startDate, Date endDate, int capacity, String sectionPhoto, CourseResponse course, String format, AddressResponse address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.sectionPhoto = sectionPhoto;
        this.course = course;
        this.format = format;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CourseResponse getCourse() {
        return course;
    }

    public void setCourse(CourseResponse course) {
        this.course = course;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public AddressResponse getAddress() {
        return address;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }

    public String getSectionPhoto() {
        return sectionPhoto;
    }

    public void setSectionPhoto(String sectionPhoto) {
        this.sectionPhoto = sectionPhoto;
    }
}
