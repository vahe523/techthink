package com.example.techthink.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne()
    private CourseSection section;

    private String name;
    private String text;
//    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;
    private String assignmentFileURL;

    public Assignment() {
    }

    public Assignment(Long id, String name, String text, Date dueDate, String assignmentFileURL) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.dueDate = dueDate;
        this.assignmentFileURL = assignmentFileURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseSection getCourseSection() {
        return section;
    }

    public void setCourseSection(CourseSection section) {
        this.section = section;
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
}
