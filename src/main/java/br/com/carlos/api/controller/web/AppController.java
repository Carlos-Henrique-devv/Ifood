package br.com.carlos.api.controller.web;

import br.com.carlos.api.model.Product;
import br.com.carlos.api.repository.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private IProduct iProduct;

    @GetMapping("/home")
    public String home(Model model) {

        List<Product> products = iProduct.findAll();

        model.addAttribute("products", products);

        return "home";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/painel";
        }
        return "login";
    }

    @GetMapping("/error")
    public String user() {
        return "error";
    }

    @GetMapping("/painel")
    public String painel() {
        return "pages/painel";
    }
}


