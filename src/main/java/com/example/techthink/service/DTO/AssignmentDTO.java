package com.example.techthink.service.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class AssignmentDTO {
    private String name;
    private String text;
    private Date dueDate;
    private String assignmentFileURL;
    private Long courseSectionId;

    public AssignmentDTO() {
    }

    public AssignmentDTO(String name, String text, Date dueDate, String assignmentFileURL, Long courseSectionId) {
        this.name = name;
        this.text = text;
        this.dueDate = dueDate;
        this.assignmentFileURL = assignmentFileURL;
        this.courseSectionId = courseSectionId;
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

    public Long getCourseSectionId() {
        return courseSectionId;
    }

    public void setCourseSectionId(Long courseSectionId) {
        this.courseSectionId = courseSectionId;
    }
}
