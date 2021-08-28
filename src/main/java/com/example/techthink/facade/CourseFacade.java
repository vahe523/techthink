package com.example.techthink.facade;


import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.model.CourseRequest;
import com.example.techthink.controller.model.CourseResponse;
import com.example.techthink.converter.Converter;
import com.example.techthink.persistence.Course;
import com.example.techthink.persistence.SubCategory;
import com.example.techthink.persistence.repository.SubCategoryCourseRepository;
import com.example.techthink.service.CourseService;
import com.example.techthink.service.DTO.CourseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Facade
public class CourseFacade {

    private final CourseService courseService;
    private final Converter converter;
    private final SubCategoryCourseRepository subCategoryCourseRepository;

    public CourseFacade(CourseService courseService, Converter converter, SubCategoryCourseRepository subCategoryCourseRepository) {
        this.courseService = courseService;
        this.converter = converter;
        this.subCategoryCourseRepository = subCategoryCourseRepository;
    }

    public CourseResponse create(CourseRequest request){
        CourseDTO courseDTO = convertToDTO(request);
        Course course = courseService.create(courseDTO);
        CourseResponse courseResponse = converter.fromCourseToResponse(course);
        return courseResponse;
    }

    public CourseResponse readById(Integer id){
        Course course = courseService.readById(id);
        CourseResponse courseResponse = converter.fromCourseToResponse(course);
//        List<SubCategory> subCategories = subCategoryCourseRepository.findSubCategories(course.getId());
//        CourseResponse courseResponse = converter.fromCourseToResponse(course, subCategories);
        return courseResponse;

    }

    public List<CourseResponse> readAll(){
        List<Course> courses = courseService.readAll();
        List<CourseResponse> collect = courses.stream()
                .map(each -> readById(each.getId()))
                .collect(Collectors.toList());
        return collect;
    }

    public CourseResponse update(Integer id, CourseRequest request){
        CourseDTO courseDTO = convertToDTO(request);
        Course updatedCourse = courseService.update(id, courseDTO);
        CourseResponse courseResponse = converter.fromCourseToResponse(updatedCourse);
//        List<SubCategory> subCategories = subCategoryCourseRepository.findSubCategories(updatedCourse.getId());
//        CourseResponse courseResponse = converter.fromCourseToResponse(updatedCourse, subCategories);
        return courseResponse;
    }

    public Boolean delete(Integer id){
        return courseService.delete(id);
    }


    private CourseDTO convertToDTO(CourseRequest request){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(request.getName());
        courseDTO.setPrice(request.getPrice());
        courseDTO.setSubCategoryIds(request.getSubCategoryIds());
        return courseDTO;
    }

}
