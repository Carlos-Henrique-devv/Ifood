package br.com.carlos.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull(message = "Imagem is require")
    private MultipartFile imagem;

    @NotBlank(message = "Name is require")
    private String name;

    @NotNull(message = "Price is require")
    private BigDecimal price;

    @NotBlank(message = "Description is require")
    private String Description;

    @NotNull(message = "Category is require")
    private Long category_id;
}
