package org.budget.plan.controller;

import org.budget.plan.entity.collection.AppUser;
import org.budget.plan.entity.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.budget.plan.entity.collection.Role.USER;

@Controller
public class TestController {
    private UserRepository repository;

    public TestController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/addUser")
    public void addUser(@RequestParam String name, @RequestParam String password) {
        AppUser user = new AppUser(name, password, List.of(USER));
        repository.save(user);
    }
}
