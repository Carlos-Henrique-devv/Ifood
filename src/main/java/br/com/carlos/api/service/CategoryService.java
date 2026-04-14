package br.com.carlos.api.service;

import br.com.carlos.api.dto.CategoryDto;
import br.com.carlos.api.exception.IdNotFoundException;
import br.com.carlos.api.exception.duplicate.DuplicateFiedException;
import br.com.carlos.api.exception.duplicate.DuplicateNameException;
import br.com.carlos.api.model.Category;
import br.com.carlos.api.repository.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private ICategory iCategory;

    public List<Category> get() {
        return iCategory.findAll();
    }

    public Category save(CategoryDto categoryDto) throws IOException {

        if (iCategory.existsByName(categoryDto.getName())) {
            throw new DuplicateNameException("Name already exists");
        }

        String imageUrl = UUID.randomUUID() + "-" + categoryDto.getImagem().getOriginalFilename();
        Path caminho = Paths.get("uploads/" +imageUrl);

        Files.createDirectories(caminho.getParent());
        Files.write(caminho, categoryDto.getImagem().getBytes());

        Category category = new Category();
        category.setImagemUrl("/uploads/" + imageUrl);
        category.setName(categoryDto.getName());
        return iCategory.save(category);
    }

    public Category update(CategoryDto categoryDto) {

        Category categoryDB = iCategory.findById(categoryDto.getId())
                .orElseThrow(() -> new IdNotFoundException("Id not found"));

        boolean notChanged = Objects.equals(categoryDB.getName(), categoryDto.getName());

        if (notChanged) {
            throw new DuplicateFiedException("Change a field");
        }

        categoryDB.setName(categoryDto.getName());
        return iCategory.save(categoryDB);
    }

    public Category delete(Long id) {
        Optional<Category> optCategory = iCategory.findById(id);
        if (optCategory.isPresent()) {
            iCategory.deleteById(id);
        } else {
            throw new IdNotFoundException("Id not faund");
        }
        return optCategory.get();
    }
}
