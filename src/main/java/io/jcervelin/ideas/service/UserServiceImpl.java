package io.jcervelin.ideas.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import io.jcervelin.ideas.entities.Role;
import io.jcervelin.ideas.entities.User;
import io.jcervelin.ideas.repository.RoleRepository;
import io.jcervelin.ideas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole;
        if (user.isAdmin()) {
            userRole = roleRepository.findByRole("ADMIN");
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        } else {
            userRole = roleRepository.findByRole("USER");
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        }

        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}