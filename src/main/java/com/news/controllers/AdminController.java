package com.news.controllers;

import com.news.WebApplication;
import com.news.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public @ResponseBody String get(@RequestParam Long id) {
        return WebApplication.GSON.toJson(adminService.get(id).get());
    }

    @PostMapping
    public @ResponseBody String add(@RequestParam Long id) {
        return WebApplication.GSON.toJson(adminService.add(id));
    }
}
