package com.example.techthink.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class AssignmentRequest {
    private String name;
    private String text;
    private Date dueDate;
    private Long sectionID;

    public AssignmentRequest() {
    }

    public AssignmentRequest(String name, String text, Date dueDate, Long sectionID) {
        this.name = name;
        this.text = text;
        this.dueDate = dueDate;
        this.sectionID = sectionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getSectionID() {
        return sectionID;
    }

    public void setSectionID(Long sectionID) {
        this.sectionID = sectionID;
    }
}
