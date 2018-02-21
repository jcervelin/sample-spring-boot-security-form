package io.jcervelin.ideas.controller;

import io.jcervelin.ideas.entities.Role;
import io.jcervelin.ideas.entities.User;
import io.jcervelin.ideas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(User user) {
        userService.saveUser(user);
        return userService.findUserByEmail(user.getEmail());
    }

    @GetMapping
    public List<User> listAll(User user) {
        userService.saveUser(user);
        return userService.findAll();
    }

    @GetMapping("/createUser")
    public void createNewUser() {
        User user = new User();
        buildFakeUser(user);
        user.setAdmin(false);
        userService.saveUser(user);
    }

    private void buildFakeUser(User user) {
        user.setActive(1);
        user.setEmail("juliano.cervelin@gmail.com");
        user.setLastName("Cervelin");
        user.setName("Juliano");
        user.setPassword("welcome1");
    }

    @GetMapping("/createAdmin")
    public void createNewAdmin() {
        User user = new User();
        buildFakeUser(user);
        user.setAdmin(true);
        userService.saveUser(user);
    }

}
