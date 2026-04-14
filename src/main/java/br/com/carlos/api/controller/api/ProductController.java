package br.com.carlos.api.controller.api;

import br.com.carlos.api.dto.ProductDto;
import br.com.carlos.api.model.Product;
import br.com.carlos.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> get() {
        return productService.get();
    }

    @GetMapping("/{id}")
    public Product getProductId(@PathVariable Long id) {
        return productService.getProductId(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public Product save(@Valid @ModelAttribute ProductDto productDto) throws IOException {
        return productService.save(productDto);
    }

    @PutMapping(consumes = "multipart/form-data")
    public Product update(@Valid @ModelAttribute  ProductDto productDto) throws IOException {
        return productService.update(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
