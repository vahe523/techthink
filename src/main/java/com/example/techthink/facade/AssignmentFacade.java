package com.example.techthink.facade;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.model.AssignmentRequest;
import com.example.techthink.controller.model.AssignmentResponse;
import com.example.techthink.converter.Converter;
import com.example.techthink.persistence.Assignment;
import com.example.techthink.service.AssignmentService;
import com.example.techthink.service.DTO.AssignmentDTO;

import java.util.List;
import java.util.stream.Collectors;

@Facade
public class AssignmentFacade {

    private final AssignmentService service;
    private final Converter converter;

    public AssignmentFacade(AssignmentService service, Converter converter) {
        this.service = service;
        this.converter = converter;
    }

    public AssignmentResponse create(AssignmentRequest request , String file){
        AssignmentDTO assignmentDTO = convertToDTO(request , file);
        Assignment assignment = service.create(assignmentDTO);
        AssignmentResponse assignmentResponse = converter.fromAssignmentToResponse(assignment);
        return assignmentResponse;
    }

    public AssignmentResponse readById(Long id){
        Assignment assignment = service.readById(id);
        AssignmentResponse assignmentResponse = converter.fromAssignmentToResponse(assignment);
        return assignmentResponse;
    }

    public List<AssignmentResponse> readAll(){
        List<Assignment> assignments = service.readAll();
        List<AssignmentResponse> assignmentResponses = assignments
                .stream()
                .map(each -> readById(each.getId()))
                .collect(Collectors.toList());
        return assignmentResponses;
    }
    public AssignmentResponse update(Long id, AssignmentRequest request , String file){
        AssignmentDTO assignmentDTO = convertToDTO(request , file);
        Assignment update = service.update(id, assignmentDTO);
        AssignmentResponse assignmentResponse = converter.fromAssignmentToResponse(update);
        return assignmentResponse;
    }

    public Boolean delete(Long id){
        Boolean delete = service.delete(id);
        return delete;
    }

    private AssignmentDTO convertToDTO(AssignmentRequest request , String file){
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setName(request.getName());
        assignmentDTO.setText(request.getText());
        assignmentDTO.setDueDate(request.getDueDate());
        assignmentDTO.setAssignmentFileURL(file);
        assignmentDTO.setCourseSectionId(request.getSectionID());
        return assignmentDTO;
    }
}
