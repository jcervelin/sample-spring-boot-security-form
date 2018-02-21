package io.jcervelin.ideas;

import io.jcervelin.ideas.entities.Role;
import io.jcervelin.ideas.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    RoleRepository repository;

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setRole("ADMIN");
        Role role2 = new Role();
        role2.setId(2);
        role2.setRole("USER");
        repository.save(role);
        repository.save(role2);

    }
}
