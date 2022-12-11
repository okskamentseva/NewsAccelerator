package com.news.services;

import com.news.DAO.AdminDAO;
import com.news.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminDAO adminDAO;

    @Autowired
    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public Optional<Admin> get(Long id) {
        return adminDAO.findById(id);
    }

    public Admin add(Long id) {
        Admin admin = new Admin();

        try {
            admin.setId(id);

            adminDAO.save(admin);
        } catch (Exception ignored) {
            admin.setId(-1L);
        }

        return admin;
    }
}
