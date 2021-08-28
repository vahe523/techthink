package com.example.techthink.service;

import com.example.techthink.persistence.CourseSection;
import com.example.techthink.service.DTO.CourseSectionDTO;

import java.util.List;

public interface CourseSectionService{

    List<CourseSection> search (String term);
    CourseSection create(CourseSectionDTO request);
    CourseSection enrollIn(Long studentId, Long sectionId);
    CourseSection readById(Long id);
    List<CourseSection> readAll();
    CourseSection update(Long id, CourseSectionDTO request);
    Boolean delete(Long id);
    CourseSection uploadPicture(Long id, String profilePicURL);

}
