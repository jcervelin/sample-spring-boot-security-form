package io.jcervelin.ideas;

import io.jcervelin.ideas.entities.Role;
import io.jcervelin.ideas.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    RoleRepository repository;

    @Value("#{'${user.roles}'.split(',')}")
    private List<String> roles;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) {
        System.out.println(roles);
        roles.forEach(r -> {
            Role role = new Role();
            role.setId(new Random().nextInt());
            role.setRole(r);
            repository.save(role);
        });

    }
}
