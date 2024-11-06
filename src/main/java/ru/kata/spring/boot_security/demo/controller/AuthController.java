package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // Отображение страницы входа
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Имя шаблона для страницы входа
    }

    // Отображение администраторской страницы
    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("message", "Это страница администратора");
        return "admin"; // Имя шаблона для страницы администратора
    }
}