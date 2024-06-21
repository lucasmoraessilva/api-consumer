package com.lucasmoraessilva.api_consumer.infrastructure.http.client;

import com.lucasmoraessilva.api_consumer.infrastructure.http.dto.TaskDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
public class TaskClient {

    private RestClient restClient;

    public TaskClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://jsonplaceholder.typicode.com").build();
    }

    public List<TaskDTO> getAllTasks() {
        return Arrays
                .stream(
                        restClient
                                .get()
                                .uri("/todos")
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .body(TaskDTO[].class))
                .toList();
    }

}
