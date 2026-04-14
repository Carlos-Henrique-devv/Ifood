package br.com.carlos.api.controller.api;

import br.com.carlos.api.dto.UserRoleDto;
import br.com.carlos.api.model.UserRole;
import br.com.carlos.api.service.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping()
    public List<UserRole> get() {
        return userRoleService.get();
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody @Valid UserRoleDto userRoleDto) {
        return ResponseEntity.status(201).body(userRoleService.save(userRoleDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid UserRoleDto userRoleDto) {
        return ResponseEntity.status(200).body(userRoleService.update(userRoleDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(userRoleService.delete(id));
    }
}
