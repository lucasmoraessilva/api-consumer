package com.lucasmoraessilva.api_consumer.business.service;

import com.lucasmoraessilva.api_consumer.business.entity.TaskEntity;
import com.lucasmoraessilva.api_consumer.business.entity.UserEntity;
import com.lucasmoraessilva.api_consumer.infrastructure.http.client.TaskClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskClient taskClient;

    public List<TaskEntity> getAllTasks() {
        return this.taskClient.getAllTasks().stream()
                .map(taskDTO -> new TaskEntity(taskDTO.getId(), UserEntity.builder().id(taskDTO.getUserId()).build(), taskDTO.getTitle(), taskDTO.isCompleted()))
                .toList();
    }

}
