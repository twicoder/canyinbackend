package com.canyin.canyinbackend.service;

import com.canyin.canyinbackend.model.Friend;
import com.canyin.canyinbackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<User, Integer> {
    Iterable<User> findByUsernameAndPassword(String username, String password);
}
