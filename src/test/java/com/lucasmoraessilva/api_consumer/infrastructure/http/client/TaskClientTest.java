package com.lucasmoraessilva.api_consumer.infrastructure.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmoraessilva.api_consumer.infrastructure.http.dto.TaskDTO;
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

@RestClientTest(TaskClient.class)
class TaskClientTest {

    @Autowired
    private TaskClient taskClient;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldGetAllTasks() throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(List.of(new TaskDTO(1,1,"test", true)));

        server.expect(requestTo(endsWith("/todos"))).andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        List<TaskDTO> tasks = taskClient.getAllTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.get(0).getId());
        assertEquals(1, tasks.get(0).getUserId());
        assertEquals("test", tasks.get(0).getTitle());
        assertTrue(tasks.get(0).isCompleted());
    }

}