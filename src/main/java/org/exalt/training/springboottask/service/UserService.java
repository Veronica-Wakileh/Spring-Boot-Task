package org.exalt.training.springboottask.service;

import lombok.AllArgsConstructor;
import org.exalt.training.springboottask.dto.*;
import org.exalt.training.springboottask.exception.OperationNotProcessedException;
import org.exalt.training.springboottask.exception.UserNotFoundException;
import org.exalt.training.springboottask.model.User;
import org.exalt.training.springboottask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(Long Id) {

        return userRepository.findById(Id).orElseThrow(() -> {throw new UserNotFoundException("User Not Found !");});

    }

    public User createNewUser(UserRequest userRequest){

        User user = new User();
        user.setName(userRequest.name());
        user.setUserRole(userRequest.userRole());
        return userRepository.save(user);
    }

    public User updateExistingUser(UserRequest userRequest, Long Id){

        User user = userRepository.findById(Id).orElseThrow(() -> {throw new OperationNotProcessedException("Updating a Non Existing User Can't be Processed");});
        user.setName(userRequest.name());
        user.setUserRole(userRequest.userRole());
        return userRepository.save(user);
    }

    public User updateUserRole(UserRequestUpdateRole userRequest, Long Id){

        User user = userRepository.findById(Id).orElseThrow(() -> {throw new OperationNotProcessedException("Updating a Non Existing User's Role Can't be Processed");});
        user.setUserRole(userRequest.userRole());
        return userRepository.save(user);
    }

    public User updateUserName(UserRequestUpdateName userRequest, Long Id){

        User user = userRepository.findById(Id).orElseThrow(() -> {throw new OperationNotProcessedException("Updating a Non Existing User's Name Can't be Processed");});
        user.setName(userRequest.name());
        return userRepository.save(user);
    }

    public void deleteExistingUser(Long Id){

        userRepository.findById(Id).orElseThrow(() -> {throw new OperationNotProcessedException("Deleting a Non Existing User Can't be Processed");});
        userRepository.deleteById(Id);
    }



}








