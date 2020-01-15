package com.canyin.canyinbackend;

import com.canyin.canyinbackend.controller.FriendController;
import com.canyin.canyinbackend.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CanyinbackendApplicationTests {

	@Autowired
	FriendController friendController;

	@Test
	public void testCreateReadDelete() {
		Friend friend = new Friend("Gordon", "Moore");
//
//		Friend friendResult = friendController.create(friend);
//
//		Iterable<Friend> friends = friendController.read();
//		Assertions.assertThat(friends).first().hasFieldOrPropertyWithValue("firstName", "Gordon");
//
//		friendController.delete(friendResult.getId());
//		Assertions.assertThat(friendController.read()).isEmpty();
	}

	@Test
	void contextLoads() {
		Assert.assertNotNull(friendController);
	}

}
