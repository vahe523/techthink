package com.example.techthink.service;

import com.example.techthink.persistence.model.Assignment;
import com.example.techthink.service.DTO.AssignmentDTO;

import java.util.List;

public interface AssignmentService {

    Assignment create(AssignmentDTO request);
    Assignment readById(Long id);
    List<Assignment> readAll();
    Assignment update(Long id, AssignmentDTO request);
    Boolean delete(Long id);
}
