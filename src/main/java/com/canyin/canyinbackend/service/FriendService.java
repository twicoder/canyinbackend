package com.canyin.canyinbackend.service;

import com.canyin.canyinbackend.model.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendService extends CrudRepository<Friend, Integer> {
}
