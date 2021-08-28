package com.example.techthink.service.impl;

import com.example.techthink.persistence.Assignment;
import com.example.techthink.persistence.CourseSection;
import com.example.techthink.persistence.repository.AssignmentRepository;
import com.example.techthink.service.AssignmentService;
import com.example.techthink.service.CourseSectionService;
import com.example.techthink.service.DTO.AssignmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseSectionService sectionService;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, CourseSectionService sectionService) {
        this.assignmentRepository = assignmentRepository;
        this.sectionService = sectionService;
    }

    @Override
    public Assignment create(AssignmentDTO request) {
        Assignment build = build(request);
        Assignment saved = assignmentRepository.save(build);
        return saved;
    }

    @Override
    public Assignment readById(Long id) {
        Assignment byId = assignmentRepository.getById(id);
        return byId;
    }

    @Override
    public List<Assignment> readAll() {
        List<Assignment> all = assignmentRepository.findAll();
        return all;
    }

    @Override
    public Assignment update(Long id, AssignmentDTO request) {
        Assignment byId = assignmentRepository.getById(id);
        Assignment subCategory = updateGivenAssignment(byId, request);
        Assignment updated = assignmentRepository.save(subCategory);
        return updated;
    }

    @Override
    public Boolean delete(Long id) {
        assignmentRepository.deleteById(id);
        return !assignmentRepository.existsById(id);
    }

    private Assignment build(AssignmentDTO request) {
        Assignment assignment = new Assignment();
        assignment.setName(request.getName());
        assignment.setText(request.getText());
        assignment.setDueDate(request.getDueDate());
        assignment.setAssignmentFileURL(request.getAssignmentFileURL());
        assignment.setCourseSection(sectionService.readById(request.getCourseSectionId()));
        return assignment;
    }

    private Assignment updateGivenAssignment(Assignment assignment, AssignmentDTO request) {
        assignment.setName(request.getName());
        assignment.setText(request.getText());
        assignment.setDueDate(request.getDueDate());
        assignment.setAssignmentFileURL(request.getAssignmentFileURL());
        CourseSection courseSection = sectionService.readById(request.getCourseSectionId());
        assignment.setCourseSection(courseSection);
        return assignment;
    }
}
