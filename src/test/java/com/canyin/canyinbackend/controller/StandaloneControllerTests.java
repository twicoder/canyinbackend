package com.canyin.canyinbackend.controller;

import com.canyin.canyinbackend.model.Friend;
import com.canyin.canyinbackend.service.FriendService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendController.class)
public class StandaloneControllerTests {

    @MockBean
    FriendService friendService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCreateReadDelete() throws Exception {
        Friend friend = new Friend("firstName", "lastName");
        List<Friend> friends = Arrays.asList(friend);

        Mockito.when(friendService.findAll()).thenReturn(friends);

        mockMvc.perform(get("/friend"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", Matchers.hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].first-name", Matchers.is("firstName")));
    }

    @Test
    public void testErrorHandlingReturnsBadRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/wrong";

        try {
            restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }
}
