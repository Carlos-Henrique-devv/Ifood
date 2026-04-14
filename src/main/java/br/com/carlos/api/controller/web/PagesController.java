package br.com.carlos.api.controller.web;

import br.com.carlos.api.model.Category;
import br.com.carlos.api.model.Product;
import br.com.carlos.api.repository.ICategory;
import br.com.carlos.api.repository.IProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PagesController {

    private final ICategory iCategory;
    private final IProduct iProduct;

    public PagesController(ICategory iCategory, IProduct iProduct) {
        this.iCategory = iCategory;
        this.iProduct = iProduct;
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        List<Category> categories = iCategory.findAll();

        model.addAttribute("categories", categories);

        return "pages/menu";
    }

    @GetMapping("/register/usuarios")
    public String register() {
        return "usuarios";
    }

    @GetMapping("/products")
    public String products(Model model) {

        List<Product> products = iProduct.findAll();

        model.addAttribute("products", products);

        return "pages/product";
    }
}
