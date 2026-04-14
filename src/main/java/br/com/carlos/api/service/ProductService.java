package br.com.carlos.api.service;

import br.com.carlos.api.dto.ProductDto;
import br.com.carlos.api.exception.IdNotFoundException;
import br.com.carlos.api.exception.duplicate.DuplicateFiedException;
import br.com.carlos.api.model.Category;
import br.com.carlos.api.model.Product;
import br.com.carlos.api.repository.ICategory;
import br.com.carlos.api.repository.IProduct;
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
public class ProductService {

    private final IProduct iProduct;
    private final ICategory iCategory;

    public ProductService(IProduct iProduct, ICategory iCategory) {
        this.iProduct = iProduct;
        this.iCategory = iCategory;
    }

    public List<Product> get() {
        return iProduct.findAll();
    }

    public Product getProductId(Long id) {
        Optional<Product> optProduct = iProduct.findById(id);

        if(optProduct.isEmpty()) {
            throw new IdNotFoundException("Id not exist");
        }

        return optProduct.get();
    }

    public Product save(ProductDto productDto) throws IOException {

        Category category = iCategory.findById(productDto.getCategory_id())
                .orElseThrow(() -> new IdNotFoundException("Id not existe"));

        String imageUrl = UUID.randomUUID() + "-" + productDto.getImagem().getOriginalFilename();
        Path caminho = Paths.get("uploads/" + imageUrl);

        Files.createDirectories(caminho.getParent());
        Files.write(caminho, productDto.getImagem().getBytes());

        Product product = new Product();
        product.setImagemUrl("/uploads/" + imageUrl);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory_id(category);
        return iProduct.save(product);
    }

    public Product update(ProductDto productDto) throws IOException {
        Product product = iProduct.findById(productDto.getId())
                .orElseThrow(() -> new IdNotFoundException("Id not existe"));

        Category category = iCategory.findById(productDto.getCategory_id())
                .orElseThrow(() -> new IdNotFoundException("Id not existe"));

        boolean notChanged = Objects.equals(product.getName(), productDto.getName())
                && Objects.equals(product.getPrice(), productDto.getPrice())
                && Objects.equals(product.getDescription(), productDto.getDescription())
                && Objects.equals(product.getCategory_id().getId(), productDto.getCategory_id());

        if(notChanged) {
            throw new DuplicateFiedException("Change a field");
        }

        String imageUrl = UUID.randomUUID() + "-" + productDto.getImagem().getOriginalFilename();
        Path caminho = Paths.get("uploads/" + imageUrl);

        Files.createDirectories(caminho.getParent());
        Files.write(caminho, productDto.getImagem().getBytes());

        product.setImagemUrl("/uploads/" + imageUrl);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory_id(category);
        return iProduct.save(product);
    }

    public void delete(Long id) {
        if (iProduct.existsById(id)) {
            iProduct.deleteById(id);
        } else {
            throw new IdNotFoundException("Id not existe");
        }
    }
}
