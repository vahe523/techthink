package com.example.techthink.facade;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.model.RegisterRequest;
import com.example.techthink.controller.model.UserResponse;
import com.example.techthink.converter.Converter;
import com.example.techthink.persistence.User;
import com.example.techthink.persistence.repository.UserSectionRepository;
import com.example.techthink.service.DTO.UserDTO;
import com.example.techthink.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Facade
public class UserFacade {


    private final UserService userService;
    private final Converter converter;
    private final UserSectionRepository userSectionRepository;

    public UserFacade(UserService userService, Converter converter, UserSectionRepository userSectionRepository) {
        this.userService = userService;
        this.converter = converter;
        this.userSectionRepository = userSectionRepository;
    }


    public UserResponse registerStudent(RegisterRequest request){
        UserDTO userDTO = convertToDTO(request);
        User registeredUser = userService.register(userDTO);
        UserResponse userResponse = converter.fromUserToResponse(registeredUser);
        return userResponse;
    }

    public UserResponse addProfessor(RegisterRequest request){
        UserDTO userDTO = convertToDTO(request);
        User professor = userService.addProfessor(userDTO);
        UserResponse userResponse = converter.fromUserToResponse(professor);
        return userResponse;
    }

    public UserResponse editProfile(Long id, RegisterRequest request){
        UserDTO userDTO = convertToDTO(request);
        User update = userService.update(id, userDTO);
        UserResponse userResponse = converter.fromUserToResponse(update);
        return userResponse;
    }

    public UserResponse readById(Long id){
        User user = userService.readById(id);
        UserResponse userResponse = converter.fromUserToResponse(user);
        return userResponse;
    }

    public List<UserResponse> readAll(){
        List<User> users = userService.readAll();
        List<UserResponse> collect = users.stream()
                .map(each -> readById(each.getId()))
                .collect(Collectors.toList());
        return collect;
    }

    public Boolean delete(Long id){
        return userService.delete(id);
    }

    public UserResponse uploadPicture(Long id, String pictureURL){
        User user = userService.uploadPicture(id, pictureURL);
        UserResponse userResponse = converter.fromUserToResponse(user);
        return userResponse;
    }

    private UserDTO convertToDTO(RegisterRequest request){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(request.getFirstName());
        userDTO.setLastName(request.getLastName());
        userDTO.setUserName(request.getUserName());
        userDTO.setEmail(request.getMail());
        userDTO.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        userDTO.setDescription(request.getDescription());
        //userDTO.setProfilePictureURL(request.getProfilePictureURL());
        return userDTO;
    }
}
