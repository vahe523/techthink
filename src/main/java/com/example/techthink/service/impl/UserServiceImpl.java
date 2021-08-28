package com.example.techthink.service.impl;


import com.example.techthink.config.UserPrincipal;
import com.example.techthink.persistence.User;
import com.example.techthink.persistence.repository.UserSectionRepository;
import com.example.techthink.persistence.repository.UserRepository;
import com.example.techthink.service.DTO.UserDTO;
import com.example.techthink.service.RoleService;
import com.example.techthink.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserSectionRepository userSectionRepository;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, UserSectionRepository userSectionRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userSectionRepository = userSectionRepository;
    }

    @Override
    public User loadByUsernameOrEmail(String term) {
        User user = userRepository.loadUserByUsernameOrEmail(term);
        return user;
    }

    @Override
    public User register(UserDTO request) {
        User user = buildUser(request);
        User savedUser = userRepository.save(user);
        User addRolledUser = addRoleToUser(savedUser.getId(), 2L);  //STUDENT
        return addRolledUser;
    }

    public User addProfessor(UserDTO request) {
        User user = buildUser(request);
        User savedUser = userRepository.save(user);
        User addRolledUser = addRoleToUser(savedUser.getId(), 3L); //PROFESSOR
        return addRolledUser;
    }

    @Override
    public User readById(Long id) {
        User byId = userRepository.getById(id);
        return byId;
    }

    @Override
    public List<User> readAll() {
        List<User> all = userRepository.findAll();
        return all;
    }

    public User update(Long id, UserDTO request) {
        User byId = userRepository.getById(id);
        User user = buildUser(byId, request);
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @Override
    public Boolean delete(Long id) {
        //first delete relationships that have user_id as a FK
        userSectionRepository.getAllUserSectionGivenUser(id).forEach(each -> userSectionRepository.deleteById(each));
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

    public User uploadPicture(Long id, String profilePicURL) {
        User byId = userRepository.getById(id);
        byId.setProfilePictureURL(profilePicURL);
        User updated = userRepository.save(byId);
        return updated;
    }

    private User buildUser(User user, UserDTO request) {
        user.setFirst_name(request.getFirstName());
        user.setLast_name(request.getLastName());
        user.setUsername(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(user.getPassword());
        user.setDescription(request.getDescription());
        user.setProfilePictureURL(request.getProfilePictureURL());
        return user;
    }

    private User buildUser(UserDTO request) {
        User user = new User();
        user.setFirst_name(request.getFirstName());
        user.setLast_name(request.getLastName());
        user.setUsername(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setDescription(request.getDescription());
        user.setProfilePictureURL(request.getProfilePictureURL());
        return user;
    }

    private User addRoleToUser(Long userId, Long roleId) {
        User byId = userRepository.getById(userId);
        byId.getRoles().add(roleService.getRoleById(roleId));
        User saved = userRepository.save(byId);
        return saved;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.loadUserByUsernameOrEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(userEntity);
    }
}
