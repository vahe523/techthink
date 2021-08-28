package com.example.techthink.persistence.repository;

import com.example.techthink.persistence.CourseSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long> {
    @Query("SELECT s FROM CourseSection s WHERE s.name LIKE %?1%")
    List<CourseSection> searchSection (String term);
}
