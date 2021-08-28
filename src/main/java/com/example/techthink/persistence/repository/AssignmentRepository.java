package com.example.techthink.persistence.repository;

import com.example.techthink.persistence.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    @Query("SELECT a.id FROM Assignment a\n" +
            "WHERE a.section.id = ?1")
    List<Long> assignmentIdGivenSectionId(Long sectionId);
}
