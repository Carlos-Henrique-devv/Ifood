package br.com.carlos.api;

import br.com.carlos.api.model.Role;
import br.com.carlos.api.model.UserRole;
import br.com.carlos.api.repository.IRole;
import br.com.carlos.api.repository.IUserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class ProjetoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoApiApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner initDataBase(IRole iRole, IUserRole iUserRole, PasswordEncoder passwordEncoder) {
        return args -> {
            if (iRole.findByName("ROLE_ADMIN").isEmpty()) {
                iRole.save(new Role("ROLE_ADMIN"));
            }

            if(iRole.findByName("ROLE_COMMON").isEmpty()) {
                iRole.save(new Role("ROLE_COMMON"));
            }

            if (iUserRole.findByUsername("admin").isEmpty()) {
                Role adminRole = iRole.findByName("ROLE_ADMIN").get();
                UserRole admin = new UserRole("admin", passwordEncoder.encode("Admin123."), Set.of(adminRole));
                iUserRole.save(admin);
            }
        };
    }
}
