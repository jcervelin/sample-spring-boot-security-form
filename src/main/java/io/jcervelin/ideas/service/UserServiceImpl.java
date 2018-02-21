package io.jcervelin.ideas.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static java.util.Objects.*;

import io.jcervelin.ideas.exceptions.InvalidEmailException;
import io.jcervelin.ideas.entities.Role;
import io.jcervelin.ideas.entities.User;
import io.jcervelin.ideas.repository.RoleRepository;
import io.jcervelin.ideas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("#{'${user.roles.user}'}")
    private String userRole;

    @Value("#{'${user.roles.admin}'}")
    private String adminRole;


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) throws InvalidEmailException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role role;
        String strRole;

        strRole = user.isAdmin() ? adminRole : userRole;

        role = roleRepository.findByRole(strRole);
        user.setRoles(new HashSet<Role>(Arrays.asList(role)));

        if (isNull(findUserByEmail(user.getEmail()))) {
            userRepository.save(user);
        } else {
            throw new InvalidEmailException("Email: "+user.getEmail()+" already exists.");
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}