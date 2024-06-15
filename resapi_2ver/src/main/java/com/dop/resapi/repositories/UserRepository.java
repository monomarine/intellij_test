package com.dop.resapi.repositories;

import com.dop.resapi.models.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public User findUserByEmail(String email) {
        User user = new User(email, "123qwe", "test user");
        return user;
    }
}
