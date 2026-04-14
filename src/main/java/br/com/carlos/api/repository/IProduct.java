package br.com.carlos.api.repository;

import br.com.carlos.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProduct extends JpaRepository<Product, Long> {
}
