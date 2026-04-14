package br.com.carlos.api.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthUserController {

    @GetMapping("/signin")
    public String signin() {
        return "auth/signin";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }
}

