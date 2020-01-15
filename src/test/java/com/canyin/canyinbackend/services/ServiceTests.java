package com.canyin.canyinbackend.services;

import com.canyin.canyinbackend.model.Friend;
import com.canyin.canyinbackend.service.FriendService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {
    @Autowired
    FriendService friendService;

    @Test
    public void testCreateReadDelete() {
        Friend friend = new Friend("firstName", "lastName");

        friendService.save(friend);

        Iterable<Friend> friends = friendService.findAll();
        Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("firstName");

        friendService.deleteAll();
        Assertions.assertThat(friendService.findAll()).isEmpty();
    }
}
