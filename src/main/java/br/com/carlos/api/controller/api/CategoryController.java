package br.com.carlos.api.controller.api;

import br.com.carlos.api.dto.CategoryDto;
import br.com.carlos.api.model.Category;
import br.com.carlos.api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> get() {
        return categoryService.get();
    }

    @PostMapping(consumes = "multipart/form-data")
    public Category save(@Valid @ModelAttribute CategoryDto categoryDto) throws IOException {
        return categoryService.save(categoryDto);
    }

    @PutMapping(consumes = "multipart/form-data")
    public Category update(@Valid @ModelAttribute CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }

    @DeleteMapping("/{id}")
    public Category delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}
