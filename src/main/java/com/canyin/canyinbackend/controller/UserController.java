package com.canyin.canyinbackend.controller;

import com.canyin.canyinbackend.model.Friend;
import com.canyin.canyinbackend.model.LoginRequest;
import com.canyin.canyinbackend.model.User;
import com.canyin.canyinbackend.service.UserService;
import com.canyin.canyinbackend.util.AuthUtil;
import com.canyin.canyinbackend.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.Iterator;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtToken jwtToken;

    @PostMapping("/register")
    User register(@Valid @RequestBody User user) {
        user.setPassword(AuthUtil.encryptPassword(user.getPassword()));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        return userService.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Iterable<User> users = userService.findByUsernameAndPassword(loginRequest.getUsername(), AuthUtil.encryptPassword(loginRequest.getPassword()));
        Iterator<User> it = users.iterator();
        if (it.hasNext()) {
            User user = it.next();
            String token = jwtToken.genJWT(user.getId());
            return token;
        } else {
            throw new ValidationException("用户名或密码错误！");
        }
    }

    @GetMapping("/users")
    Iterable<User> read() {
        return userService.findAll();
    }
}
