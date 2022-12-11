package com.news.services;

import com.news.DAO.UsersDAO;
import com.news.WebApplication;
import com.news.models.Users;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    private final UsersDAO usersDAO;

    public UsersService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public Users add(String email, String name, String password) {
        try {
            Users user = new Users();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);

            usersDAO.save(user);

            return user;
        } catch (Exception e) {
            return nullUser();
        }
    }

    public Optional<Users> get(Long id) {
        return usersDAO.findById(id);
    }

    public Optional<Users> delete(Long id) {
        Optional<Users> optionalUsers = usersDAO.findById(id);

        optionalUsers.ifPresent(usersDAO::delete);

        return optionalUsers;
    }

    private @NotNull Users nullUser() {
        Users user = new Users();
        user.setEmail(null);
        user.setName(null);
        user.setPassword(null);

        return user;
    }
}
