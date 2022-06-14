package com.example.springboottask20;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public List<User> getAll() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
}
