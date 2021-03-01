package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.entity.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameAlreadyExistException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNotfoundException;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class UserService {

    List<User> userList;

    public UserService() {
        this.userList = new ArrayList<>();
        this.userList.add(new User("user1", "12345678", "user1@gmail.com"));
        this.userList.add(new User("user2", "12345678", "user2@gmail.com"));
        this.userList.add(new User("user3", "12345678", "user3@gmail.com"));
    }

    public void registerUser(User user) {

        if (isUserNameExist(user)) {
            throw new UserNameAlreadyExistException("用户已存在");
        }
        this.userList.add(user);
    }

    private boolean isUserNameExist(User user) {
        return this.userList.stream()
                .anyMatch(userObject -> userObject.getUsername().equals(user.getUsername()));
    }

    public User getUserByNameAndPassword(String username, String password) {

        return userList.stream().
                filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findAny()
                .orElseThrow(() -> new UserNotfoundException("用户名或密码错误~~~"));
    }

}
