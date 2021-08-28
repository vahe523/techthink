package com.example.techthink.facade;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.model.CourseSectionRequest;
import com.example.techthink.controller.model.CourseSectionResponse;
import com.example.techthink.controller.model.SectionParticipantsResponse;
import com.example.techthink.converter.Converter;
import com.example.techthink.persistence.CourseSection;
import com.example.techthink.service.DTO.CourseSectionDTO;
import com.example.techthink.service.impl.CourseSectionServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Facade
public class SectionFacade {

    private final CourseSectionServiceImpl sectionService;
    private final Converter converter;

    public SectionFacade(CourseSectionServiceImpl sectionService, Converter converter) {
        this.sectionService = sectionService;
        this.converter = converter;
    }

    public CourseSectionResponse createSection(Long professorId, CourseSectionRequest request) {
        CourseSectionDTO sectionDTO = convertToDTO(professorId, request);
        CourseSection courseSection = sectionService.create(sectionDTO);
        CourseSectionResponse courseSectionResponse = converter.fromSectionToResponse(courseSection);
        return courseSectionResponse;
    }

    public SectionParticipantsResponse enrollIn(Long studentId, Long sectionId) {
        CourseSection section = sectionService.enrollIn(studentId, sectionId);
        SectionParticipantsResponse sectionParticipantsResponse = converter.fromSectionToResponseWithParticipants(section);
        return sectionParticipantsResponse;
    }

    public CourseSectionResponse readById(Long id) {
        CourseSection section = sectionService.readById(id);
        CourseSectionResponse courseSectionResponse = converter.fromSectionToResponse(section);
        return courseSectionResponse;
    }

    public List<CourseSectionResponse> readAll() {
        List<CourseSection> courseSections = sectionService.readAll();
        List<CourseSectionResponse> collect = courseSections.stream()
                .map(each -> readById(each.getId()))
                .collect(Collectors.toList());
        return collect;
    }

    public CourseSectionResponse update(Long id, CourseSectionRequest request) {
        CourseSectionDTO courseSectionDTO = convertToDTOWithoutUpdatingProfessor(request);
        CourseSection update = sectionService.update(id, courseSectionDTO);
        CourseSectionResponse courseSectionResponse = converter.fromSectionToResponse(update);
        return courseSectionResponse;
    }

    public Boolean delete(Long id) {
        return sectionService.delete(id);
    }

    //this might be excluded, probably an error
    public Boolean deleteStudentFromSection(Long studentId, Long sectionId) {
        Boolean aBoolean = sectionService.deleteStudentFromSection(studentId, sectionId);
        return aBoolean;
    }

    public CourseSectionResponse setSectionPic(Long sectionId, String picURL) {
        CourseSection section = sectionService.uploadPicture(sectionId, picURL);
        CourseSectionResponse courseSectionResponse = converter.fromSectionToResponse(section);
        return courseSectionResponse;
    }

    public List<CourseSectionResponse> search(String term) {
        List<CourseSection> searched = sectionService.search(term);
        List<CourseSectionResponse> searchedList = searched
                .stream()
                .map(converter::fromSectionToResponse)
                .collect(Collectors.toList());
        return searchedList;
    }

    //????????
    private CourseSectionDTO convertToDTOWithoutUpdatingProfessor(CourseSectionRequest request) {
        CourseSectionDTO courseSectionDTO = new CourseSectionDTO();
        courseSectionDTO.setName(request.getName());
        courseSectionDTO.setDescription(request.getDescription());
        courseSectionDTO.setStartDate(request.getStartDate());
        courseSectionDTO.setEndDate(request.getEndDate());
        courseSectionDTO.setCapacity(request.getCapacity());
        //courseSectionDTO.setPhotoURL(request.getPhotoURL());
        courseSectionDTO.setCourseId(request.getCourseId());
        courseSectionDTO.setFormatId(request.getFormatId());
        courseSectionDTO.setAddressId(request.getAddressId());
        return courseSectionDTO;

    }


    private CourseSectionDTO convertToDTO(Long professorId, CourseSectionRequest request) {
        CourseSectionDTO courseSectionDTO = new CourseSectionDTO();
        courseSectionDTO.setUserId(professorId);
        courseSectionDTO.setName(request.getName());
        courseSectionDTO.setDescription(request.getDescription());
        courseSectionDTO.setStartDate(request.getStartDate());
        courseSectionDTO.setEndDate(request.getEndDate());
        courseSectionDTO.setCapacity(request.getCapacity());
        //courseSectionDTO.setPhotoURL(request.getPhotoURL());
        courseSectionDTO.setCourseId(request.getCourseId());
        courseSectionDTO.setFormatId(request.getFormatId());
        courseSectionDTO.setAddressId(request.getAddressId());
        return courseSectionDTO;

    }
}
