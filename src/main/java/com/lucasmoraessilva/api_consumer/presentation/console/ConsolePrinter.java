package com.lucasmoraessilva.api_consumer.presentation.console;

import com.lucasmoraessilva.api_consumer.business.entity.TaskEntity;
import com.lucasmoraessilva.api_consumer.business.entity.UserEntity;

import java.util.List;

public class ConsolePrinter {

    public static void printUserData(List<UserEntity> users) {
        System.out.println("\nUsers Summary\n");

        users.forEach(
                user -> {
                    Integer totalTasks = user.getTasks().size();
                    Long completedTasks = user.getTasks().stream().filter(TaskEntity::isCompleted).count();
                    Long uncompletedTasks = user.getTasks().stream().filter(task -> !task.isCompleted()).count();

                    System.out.println(
                            String.format(
                                    "User: %s\nTotal tasks: %d\nCompleted tasks: %d\nUncompleted tasks: %d\n",
                                    user.getName(),
                                    totalTasks,
                                    completedTasks,
                                    uncompletedTasks));
                });
    }

}
