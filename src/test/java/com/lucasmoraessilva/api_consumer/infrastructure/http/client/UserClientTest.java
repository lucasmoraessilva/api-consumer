package com.lucasmoraessilva.api_consumer.infrastructure.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmoraessilva.api_consumer.infrastructure.http.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(UserClient.class)
class UserClientTest {

    @Autowired
    private UserClient userClient;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldGetAllUsers() throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(List.of(new UserDTO(1,"test", "test@test.com.br")));

        server.expect(requestTo(endsWith("/users"))).andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        List<UserDTO> users = userClient.getAllUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.get(0).getId());
        assertEquals("test", users.get(0).getName());
        assertEquals("test@test.com.br", users.get(0).getEmail());
    }
}