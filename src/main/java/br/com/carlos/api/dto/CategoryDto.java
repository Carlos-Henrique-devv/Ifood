package br.com.carlos.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    @NotNull(message = "Imagem is require")
    private MultipartFile imagem;

    @NotBlank(message = "Name is require")
    private String name;
}
