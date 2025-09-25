package com.gcu.workspacecst339.business;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gcu.workspacecst339.data.UserRepository;
import com.gcu.workspacecst339.model.User;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) { this.users = users; }

    public boolean usernameTaken(String username) {
        return users.existsByUsername(username);
    }

    public User register(String username, String password, String firstName, String lastName, String email) {
        User u = new User(null, username, password, firstName, lastName, email);
        return users.save(u);
    }

    public Optional<User> authenticate(String username, String password) {
        return users.findByUsername(username)
                    .filter(u -> u.getPassword().equals(password));
    }
}


