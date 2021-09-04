package com.example.techthink.converter;

import com.example.techthink.annotation.Convert;
import com.example.techthink.controller.model.*;
import com.example.techthink.persistence.model.*;
import com.example.techthink.persistence.repository.SubCategoryCourseRepository;
import com.example.techthink.persistence.repository.UserSectionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Convert
public class Converter {

    //ALTERNATIVE????
    private final SubCategoryCourseRepository subCategoryCourseRepository;
    private final UserSectionRepository userSectionRepository;

    public Converter(SubCategoryCourseRepository subCategoryCourseRepository, UserSectionRepository userSectionRepository) {
        this.subCategoryCourseRepository = subCategoryCourseRepository;
        this.userSectionRepository = userSectionRepository;
    }

    public UserResponse fromUserToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirst_name(user.getFirst_name());
        userResponse.setLast_name(user.getLast_name());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setDescription(user.getDescription());
        userResponse.setProfilePictureURL(user.getProfilePictureURL());
        userResponse.setRoleName(user.getRoles().stream()
                .map(each -> each.getName().toString())
                .collect(Collectors.toList()));

        List<CourseSection> allSectionsGivenUser = userSectionRepository.findAllSectionsGivenUser(user.getId());
        List<CourseSectionResponse> takenSections = allSectionsGivenUser.stream()
                .map(each -> fromSectionToResponse(each))
                .collect(Collectors.toList());
        userResponse.setTakenCourseSections(takenSections);

        return userResponse;
    }

    public CourseSectionResponse fromSectionToResponse(CourseSection section) {
        CourseSectionResponse courseSectionResponse = new CourseSectionResponse();
        courseSectionResponse.setId(section.getId());
        courseSectionResponse.setName(section.getName());
        courseSectionResponse.setDescription(section.getDescription());
        courseSectionResponse.setStartDate(section.getStart_date());
        courseSectionResponse.setEndDate(section.getEnd_date());
        courseSectionResponse.setCapacity(section.getCapacity());
        courseSectionResponse.setCourse(fromCourseToResponse(section.getCourse()));
        courseSectionResponse.setFormat(section.getFormat().getName().toString());
        courseSectionResponse.setAddress(fromAddressToResponse(section.getAddress()));
        return courseSectionResponse;
    }

    public SectionParticipantsResponse fromSectionToResponseWithParticipants(CourseSection section) {
        SectionParticipantsResponse sectionParticipantsResponse = new SectionParticipantsResponse();
        CourseSectionResponse courseSectionResponse = fromSectionToResponse(section);
        sectionParticipantsResponse.setSectionResponse(courseSectionResponse);

        List<User> allUsersGivenSection = userSectionRepository.findAllUsersGivenSection(section.getId());
        List<UserResponse> participants = allUsersGivenSection.stream()
                .map(each -> fromUserToResponse(each)).collect(Collectors.toList());

        sectionParticipantsResponse.setParticipants(participants);
        return sectionParticipantsResponse;
    }

    public AddressResponse fromAddressToResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setCountry(address.getCountry());
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        return addressResponse;
    }

    public List<AddressResponse> fromAddressToResponseList(List<Address> addresses) {
        return addresses.stream()
                .map(each -> fromAddressToResponse(each))
                .collect(Collectors.toList());
    }

    public CategoryResponse fromCategoryToResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }

    public List<CategoryResponse> fromCategoryToResponseList(List<Category> categories) {
        return categories.stream()
                .map(each -> fromCategoryToResponse(each))
                .collect(Collectors.toList());
    }

    public SubCategoryResponse fromSubCategoryToResponse(SubCategory subCategory) {
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        subCategoryResponse.setId(subCategory.getId());
        subCategoryResponse.setName(subCategory.getName());
        CategoryResponse categoryResponse = fromCategoryToResponse(subCategory.getCategory());
        subCategoryResponse.setCategory(categoryResponse);
        return subCategoryResponse;
    }

    public List<SubCategoryResponse> fromSubCategoryToResponseList(List<SubCategory> subCategories) {
        return subCategories.stream()
                .map(each -> fromSubCategoryToResponse(each))
                .collect(Collectors.toList());
    }

    public AssignmentResponse fromAssignmentToResponse(Assignment assignment) {
        AssignmentResponse assignmentResponse = new AssignmentResponse();
        assignmentResponse.setId(assignment.getId());
        assignmentResponse.setName(assignment.getName());
        assignmentResponse.setText(assignment.getText());
        assignmentResponse.setAssignmentFileURL(assignment.getAssignmentFileURL());
        assignmentResponse.setDueDate(assignment.getDueDate());
        CourseSectionResponse sectionResponse = fromSectionToResponse(assignment.getCourseSection());
        assignmentResponse.setSection(sectionResponse);
        return assignmentResponse;
    }

    public CourseResponse fromCourseToResponse(Course course) {
        List<SubCategory> subCategories = subCategoryCourseRepository.findSubCategories(course.getId());
        CourseResponse courseResponse = fromCourseToResponseGivenSubCategories(course, subCategories);
        return courseResponse;
    }

    public CourseResponse fromCourseToResponseGivenSubCategories(Course course, List<SubCategory> subCategories) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setName(course.getName());
        courseResponse.setPrice(course.getPrice());

        List<SubCategoryResponse> subCategoryResponses = subCategories.stream()
                .map(each -> fromSubCategoryToResponse(each))
                .collect(Collectors.toList());
        courseResponse.setSubCategoryResponses(subCategoryResponses);
        return courseResponse;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
