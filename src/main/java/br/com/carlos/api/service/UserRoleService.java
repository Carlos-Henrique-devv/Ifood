package br.com.carlos.api.service;

import br.com.carlos.api.dto.UserRoleDto;
import br.com.carlos.api.exception.IdNotFoundException;
import br.com.carlos.api.exception.duplicate.DuplicateFiedException;
import br.com.carlos.api.exception.duplicate.DuplicateUserNameException;
import br.com.carlos.api.model.Role;
import br.com.carlos.api.model.UserRole;
import br.com.carlos.api.repository.IRole;
import br.com.carlos.api.repository.IUserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserRoleService {

    private final IRole iRole;
    private final IUserRole iUserRole;
    private final PasswordEncoder passwordEncoder;

    public UserRoleService(IRole iRole, IUserRole iUserRole) {
        this.iRole = iRole;
        this.iUserRole = iUserRole;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserRole> get() {
        return iUserRole.findAll();
    }

    public UserRole save(UserRoleDto userRoleDto) {

        if(iUserRole.existsByUsername(userRoleDto.getUsername())) {
            throw new DuplicateUserNameException("exist Username");
        }

        UserRole userRole = new UserRole();

        userRole.setUsername(userRoleDto.getUsername());
        userRole.setPassword(passwordEncoder.encode(userRoleDto.getPassword()));

        Optional<Role> optRole = iRole.findByName("ROLE_COMMON");

        optRole.ifPresent(findByRole -> userRole.setRoles(Set.of(findByRole)));

        return iUserRole.save(userRole);
    }

    public UserRole update(UserRoleDto userRoleDto) {

        UserRole userRole = iUserRole.findById(userRoleDto.getId())
                .orElseThrow(() -> new IdNotFoundException("Id not existe"));

        if(iUserRole.existsByUsername(userRoleDto.getUsername())) {
            throw new DuplicateUserNameException("Username already exists");
        }

        boolean notChanged = Objects.equals(userRole.getUsername(), userRoleDto.getUsername())
                && Objects.equals(userRole.getPassword(), userRoleDto.getPassword());

        if(notChanged) {
            throw new DuplicateFiedException("Change a field");
        }

        userRole.setUsername(userRoleDto.getUsername());
        userRole.setPassword(passwordEncoder.encode(userRole.getPassword()));

        return iUserRole.save(userRole);
    }

    public UserRole delete(Integer id) {
        Optional<UserRole> optUserRole = iUserRole.findById(id);
        if(optUserRole.isPresent()) {
            iUserRole.deleteById(id);
        } else {
            throw new IdNotFoundException("Id not existe");
        }

        return optUserRole.get();
    }
}
