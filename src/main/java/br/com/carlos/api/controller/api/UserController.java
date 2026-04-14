package br.com.carlos.api.controller.api;

import br.com.carlos.api.dto.LoginRequestDto;
import br.com.carlos.api.dto.UserDto;
import br.com.carlos.api.model.User;
import br.com.carlos.api.token.Token;
import br.com.carlos.api.token.TokenUtil;
import br.com.carlos.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> get() {
        return ResponseEntity.status(200).body(userService.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(201).body(userService.save(userDto));
    }

    @PutMapping
    public ResponseEntity<?> Update(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(201).body(userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.deleter(id);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> valid(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        userService.valid(loginRequestDto);

        String createToken = TokenUtil.createToken(loginRequestDto);
        Token token = new Token(createToken);

        return ResponseEntity.ok(token);
    }
}