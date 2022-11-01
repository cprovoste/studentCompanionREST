package com.studentcompanion.rest.controllers;

import com.studentcompanion.rest.models.User;
import com.studentcompanion.rest.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/users/index")
    public String listarUsuarios(HttpSession session, Model model)
    {
        List<User> users = repository.findAll();

        model.addAttribute("users", users);
        return "/usuarios/listar";
    }

    @GetMapping("/users/agregar")
    public String agregarUsuario(HttpSession session, Model model)
    {
        model.addAttribute("user", new User());
        return "/usuarios/agregar";
    }

    @PostMapping("/users/procesar-agregar")
    public String procesarAgregarUsuario(HttpSession session, Model model, @ModelAttribute User user)
    {
        repository.save(user);
        return "redirect:/users/index";
    }
}
