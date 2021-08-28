package com.example.techthink.persistence.repository;

import com.example.techthink.persistence.SubCategory;
import com.example.techthink.persistence.SubCategory_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCategoryCourseRepository extends JpaRepository<SubCategory_Course, Integer> {


    @Query("SELECT sub from SubCategory sub\n" +
            "join SubCategory_Course scc on sub.id = scc.subCategory.id\n" +
            "where scc.course.id= ?1")
    List<SubCategory> findSubCategories(Integer courseId);

    @Query("Select scc.id from SubCategory_Course scc\n" +
            "join Course c on scc.course.id = c.id\n" +
            "where scc.course.id = ?1")
    List<Integer> getAllSubCategoryCourseGivenCourse(Integer id);


}
