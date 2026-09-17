package org.exalt.training.springboottask.controller;

import lombok.AllArgsConstructor;
import org.exalt.training.springboottask.dto.*;
import org.exalt.training.springboottask.model.User;
import org.exalt.training.springboottask.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/select")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/select/{Id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User findOneUser(@PathVariable Long Id) {
        return userService.getUserByID(Id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createTicket(@RequestBody UserRequest userRequest) {
        return userService.createNewUser(userRequest);
    }

    @PutMapping("/update/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public String updateProject(@RequestBody UserRequest userRequest, @PathVariable Long Id) {

        userService.updateExistingUser(userRequest, Id);
        return "User Updated Successfully !";
    }

    @PatchMapping("/update/role/{Id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String updateTicketStatus(@PathVariable Long Id, @RequestBody UserRequestUpdateRole role) {

        userService.updateUserRole(role, Id);
        return "User Role Updated Successfully !";
    }

    @PatchMapping("/update/name/{Id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String updateTicketStatus(@PathVariable Long Id, @RequestBody UserRequestUpdateName role) {

        userService.updateUserName(role, Id);
        return "User Name Updated Successfully !";
    }

    @DeleteMapping("/delete/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long Id) {
        userService.deleteExistingUser(Id);
    }
}
