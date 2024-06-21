package com.lucasmoraessilva.api_consumer.infrastructure.http.client;

import com.lucasmoraessilva.api_consumer.infrastructure.http.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
public class UserClient {

    private RestClient restClient;

    public UserClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://jsonplaceholder.typicode.com").build();
    }

    public List<UserDTO> getAllUsers() {
        return Arrays
                .stream(
                        restClient
                                .get()
                                .uri("/users")
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .body(UserDTO[].class))
                .toList();
    }

}
