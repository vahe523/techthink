package com.example.techthink.service.impl;

import com.example.techthink.persistence.CourseSection;
import com.example.techthink.persistence.User;
import com.example.techthink.persistence.User_Section;
import com.example.techthink.persistence.repository.AssignmentRepository;
import com.example.techthink.persistence.repository.CourseSectionRepository;
import com.example.techthink.persistence.repository.UserSectionRepository;
import com.example.techthink.service.CourseSectionService;
import com.example.techthink.service.DTO.CourseSectionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSectionServiceImpl implements CourseSectionService {

    private final AddressServiceImpl addressService;
    private final FormatServiceImpl formatService;
    private final CourseServiceImpl courseService;
    private final UserServiceImpl userService;
    private final UserSectionRepository userSectionRepository;
    private final CourseSectionRepository sectionRepository;
    private final AssignmentRepository assignmentRepository;

    public CourseSectionServiceImpl(AddressServiceImpl addressService, FormatServiceImpl formatService, CourseServiceImpl courseService, UserServiceImpl userService, UserSectionRepository userSectionRepository, CourseSectionRepository sectionRepository, AssignmentRepository assignmentRepository) {
        this.addressService = addressService;
        this.formatService = formatService;
        this.courseService = courseService;
        this.userService = userService;
        this.userSectionRepository = userSectionRepository;
        this.sectionRepository = sectionRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public List<CourseSection> search(String term) {
        return sectionRepository.searchSection(term);
    }

    @Override
    public CourseSection create(CourseSectionDTO request) {
        User professor = userService.readById(request.getUserId());
        CourseSection courseSection = new CourseSection();
        CourseSection section = buildSection(courseSection, request);
        CourseSection savedSection = sectionRepository.save(section);
        connectUserAndSection(professor, savedSection);
        return savedSection;
    }


    @Override
    public CourseSection enrollIn(Long studentId, Long sectionId) {
        User student = userService.readById(studentId);
        CourseSection section = readById(sectionId);
        connectUserAndSection(student, section);
        return section;  //??
    }

    @Override
    public CourseSection readById(Long id) {
        CourseSection byId = sectionRepository.getById(id);
        return byId;
    }

    @Override
    public List<CourseSection> readAll() {
        List<CourseSection> all = sectionRepository.findAll();
        return all;
    }

    @Override
    public CourseSection update(Long id, CourseSectionDTO request) {
        CourseSection byId = sectionRepository.getById(id);
        CourseSection section = buildSection(byId, request);
        CourseSection updated = sectionRepository.save(section);
        return updated;

    }

    @Override
    public Boolean delete(Long id) {
        //first delete the relationship between given section and users;
        List<Long> allUserSectionGivenSection = userSectionRepository.getAllUserSectionGivenSection(id);
        allUserSectionGivenSection.forEach(each -> userSectionRepository.deleteById(each));

        //delete all assignments of the given section before deleting section
        List<Long> allAssignmentGivenSection = assignmentRepository.assignmentIdGivenSectionId(id);
        allAssignmentGivenSection.forEach(each -> assignmentRepository.deleteById(each));;

        sectionRepository.deleteById(id);
        return !sectionRepository.existsById(id);
    }

    public CourseSection uploadPicture(Long id, String profilePicURL){
        CourseSection byId = sectionRepository.getById(id);
        byId.setPhotoURL(profilePicURL);
        CourseSection withPic = sectionRepository.save(byId);
        return withPic;
    }

    public Boolean deleteStudentFromSection(Long studentId, Long sectionId){
        Long relationshipId = userSectionRepository.relationshipIdGivenStudentAndSection(studentId, sectionId);
        userSectionRepository.deleteById(relationshipId);
        return !userSectionRepository.existsById(relationshipId);

    }

    private void connectUserAndSection(User user, CourseSection section){
        User_Section user_section = new User_Section();
        user_section.setCourseSection(section);
        user_section.setUser(user);
        userSectionRepository.save(user_section);
    }

    private CourseSection buildSection(CourseSection courseSection, CourseSectionDTO request){
        courseSection.setName(request.getName());
        courseSection.setDescription(request.getDescription());
        courseSection.setStart_date(request.getStartDate());
        courseSection.setEnd_date(request.getEndDate());
        courseSection.setCapacity(request.getCapacity());
        courseSection.setPhotoURL(request.getPhotoURL());
        courseSection.setCourse(courseService.readById(request.getCourseId()));
        courseSection.setFormat(formatService.getFormatById(request.getFormatId()));
        courseSection.setAddress(addressService.readById(request.getAddressId()));
        return courseSection;

    }
}
