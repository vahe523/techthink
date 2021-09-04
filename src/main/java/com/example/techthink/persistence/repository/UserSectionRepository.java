package com.example.techthink.persistence.repository;

import com.example.techthink.persistence.model.CourseSection;
import com.example.techthink.persistence.model.User;
import com.example.techthink.persistence.model.User_Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserSectionRepository extends JpaRepository<User_Section, Long> {

    @Query("Select userSection.id from User_Section userSection\n" +
            "join User u on userSection.user.id = u.id\n" +
            "where userSection.user.id = ?1")
    List<Long> getAllUserSectionGivenUser(Long id);

    @Query("Select userSection.id from User_Section userSection\n" +
            "join CourseSection section on userSection.courseSection.id = section.id\n" +
            "where userSection.courseSection.id = ?1")
    List<Long> getAllUserSectionGivenSection(Long id);


    //given user, return all sections tht the user is enrolled
    @Query("SELECT section from CourseSection section\n" +
            "join User_Section userSection on section.id = userSection.courseSection.id\n" +
            "where userSection.user.id= ?1")
    List<CourseSection> findAllSectionsGivenUser(Long UserId);

    //given section, return all participants i.e. users
    @Query("SELECT user from User user\n" +
            "join User_Section userSection on user.id = userSection.user.id\n" +
            "where userSection.courseSection.id= ?1")
    List<User> findAllUsersGivenSection(Long SectionId);

    //given student id and section id, return the user_section id
    @Query("SELECT userSection.id FROM User_Section userSection\n" +
            "WHERE userSection.user.id = ?1\n" +
            "AND userSection.courseSection.id = ?2")
    Long relationshipIdGivenStudentAndSection(Long studentId, Long sectionId);

}
