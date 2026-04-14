package br.com.carlos.api.repository;

import br.com.carlos.api.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRole extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByUsername(String username);
    boolean existsByUsername(String username);
}
