package com.example.techthink.service;

import com.example.techthink.persistence.Course;
import com.example.techthink.service.DTO.CourseDTO;

import java.util.List;

public interface CourseService {
    Course create(CourseDTO request);
    Course readById(Integer id);
    List<Course> readAll();
    Course update(Integer id, CourseDTO request);
    Boolean delete(Integer id);

}