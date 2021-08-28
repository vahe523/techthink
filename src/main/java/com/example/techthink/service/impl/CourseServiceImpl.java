package com.example.techthink.service.impl;


import com.example.techthink.persistence.Course;
import com.example.techthink.persistence.SubCategory;
import com.example.techthink.persistence.SubCategory_Course;
import com.example.techthink.persistence.repository.CourseRepository;
import com.example.techthink.persistence.repository.SubCategoryCourseRepository;
import com.example.techthink.service.CourseService;
import com.example.techthink.service.DTO.CourseDTO;
import com.example.techthink.service.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final SubCategoryService subCategoryService;
    private final SubCategoryCourseRepository subCategoryCourseRepository;

    public CourseServiceImpl(CourseRepository courseRepository, SubCategoryService subCategoryService, SubCategoryCourseRepository subCategoryCourseRepository) {
        this.courseRepository = courseRepository;
        this.subCategoryService = subCategoryService;
        this.subCategoryCourseRepository = subCategoryCourseRepository;
    }

    @Override
    public Course create(CourseDTO request) {
        Course build = build(request);
        Course savedCourse = courseRepository.save(build);
        List<Integer> subCategoryIds = request.getSubCategoryIds();
        List<SubCategory> subCategories = subCategoryIds.stream()
                .map(each -> {
                    SubCategory subCategory = subCategoryService.readById(each);
                    return subCategory;
                }).collect(Collectors.toList());
        connectCourseWithSubCategory(savedCourse, subCategories);
        return savedCourse;
    }


    @Override
    public Course readById(Integer id) {
        Course byId = courseRepository.getById(id);
        return byId;
    }

    @Override
    public List<Course> readAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course update(Integer id, CourseDTO request) {
        Course byId = courseRepository.getById(id);
        Course updated = updateGivenCourse(byId, request);
        Course saved = courseRepository.save(updated); //maybe saving is in updateGivenCourse
        return saved;
    }


    @Override
    public Boolean delete(Integer id) {
        //first delete the relationship, then the course
        List<Integer> allSubCategoryCourseGivenCourse = subCategoryCourseRepository.getAllSubCategoryCourseGivenCourse(id);
        allSubCategoryCourseGivenCourse.forEach(each -> subCategoryCourseRepository.deleteById(each));

        courseRepository.deleteById(id);
        return !courseRepository.existsById(id);
    }

    private Course updateGivenCourse(Course course, CourseDTO request){
        course.setName(request.getName());
        course.setPrice(request.getPrice());
        //maybe saving course here?
        List<SubCategory> subCategories = request.getSubCategoryIds().stream()
                .map(each -> subCategoryService.readById(each)).collect(Collectors.toList());
        connectCourseWithSubCategory(course, subCategories);
        return course;
    }

    private void connectCourseWithSubCategory(Course course, List<SubCategory> subCategories){
        subCategories.stream()
                .map(each -> {
                    SubCategory_Course subCategory_course = new SubCategory_Course();
                    subCategory_course.setCourse(course);
                    subCategory_course.setSubCategory(each);
                    SubCategory_Course saved = subCategoryCourseRepository.save(subCategory_course);
                    return saved;
                })
                .collect(Collectors.toList());
    }

    private Course build(CourseDTO request){
        Course course = new Course();
        course.setName(request.getName());
        course.setPrice(request.getPrice());
        return course;

    }
}
