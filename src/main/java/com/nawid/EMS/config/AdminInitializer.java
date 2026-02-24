package com.nawid.EMS.config;

import com.nawid.EMS.security.user.entity.Role;
import com.nawid.EMS.security.user.entity.User;
import com.nawid.EMS.security.user.entity.UserRepo;
import com.nawid.EMS.security.user.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {



    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {

        String adminUsername = "admin";

        boolean exists = userRepository.existsByUsername(adminUsername);

        if (!exists) {

            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ROLE_ADMIN);

            userRepository.save(admin);

            System.out.println("Default admin user created.");
        } else {
            System.out.println("Admin already exists.");
        }
    }
}