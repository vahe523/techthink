package com.example.techthink.persistence.model;

import javax.persistence.*;

@Entity
public class User_Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private CourseSection courseSection;

    public User_Section() {

    }

    public User_Section(Long id, User user, CourseSection courseSection) {
        this.id = id;
        this.user = user;
        this.courseSection = courseSection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CourseSection getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(CourseSection courseSection) {
        this.courseSection = courseSection;
    }
}
