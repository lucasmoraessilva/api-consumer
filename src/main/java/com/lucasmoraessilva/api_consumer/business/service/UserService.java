package com.lucasmoraessilva.api_consumer.business.service;

import com.lucasmoraessilva.api_consumer.business.entity.TaskEntity;
import com.lucasmoraessilva.api_consumer.business.entity.UserEntity;
import com.lucasmoraessilva.api_consumer.infrastructure.http.client.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserClient userClient;

    public List<UserEntity> getAllUser() {
        return this.userClient.getAllUsers().stream()
                .map(userDTO -> new UserEntity(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), null))
                .toList();
    }

    public List<UserEntity> mergeTasksWithUsers(List<UserEntity> users, List<TaskEntity> tasks) {
        Map<Integer, List<TaskEntity>> taskGroup = tasks.stream().collect(Collectors.groupingBy(task -> task.getResponsible().getId()));

        users.forEach(user -> {
            if (user.getTasks() == null) {
                user.setTasks(new HashSet<TaskEntity>());
            }

            user.getTasks().addAll(taskGroup.get(user.getId()));
            user.getTasks().forEach(task -> task.setResponsible(user));
        });

        return new ArrayList<UserEntity>(users);
    }

}
