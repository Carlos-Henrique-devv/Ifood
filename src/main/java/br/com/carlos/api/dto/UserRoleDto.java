package br.com.carlos.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleDto {

    private Integer id;

    @NotBlank(message = "username is require")
    private String username;

    @NotBlank(message = "password is require")
    private String password;
}
