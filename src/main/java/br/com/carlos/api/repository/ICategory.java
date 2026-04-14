package br.com.carlos.api.repository;

import br.com.carlos.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategory extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
