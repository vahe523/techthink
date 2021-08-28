package com.example.techthink.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class AssignmentResponse {
    private Long id;
    private String name;
    private String text;
    private Date dueDate;
    private String assignmentFileURL;
    private CourseSectionResponse section;

    public AssignmentResponse() {
    }

    public AssignmentResponse(Long id, String name, String text, Date dueDate, String assignmentFileURL, CourseSectionResponse section) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.dueDate = dueDate;
        this.assignmentFileURL = assignmentFileURL;
        this.section = section;
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

    public String getAssignmentFileURL() {
        return assignmentFileURL;
    }

    public void setAssignmentFileURL(String assignmentFileURL) {
        this.assignmentFileURL = assignmentFileURL;
    }

    public CourseSectionResponse getSection() {
        return section;
    }

    public void setSection(CourseSectionResponse section) {
        this.section = section;
    }
}
