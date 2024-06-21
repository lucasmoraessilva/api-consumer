package com.lucasmoraessilva.api_consumer;

import com.lucasmoraessilva.api_consumer.business.entity.TaskEntity;
import com.lucasmoraessilva.api_consumer.business.entity.UserEntity;
import com.lucasmoraessilva.api_consumer.business.service.TaskService;
import com.lucasmoraessilva.api_consumer.business.service.UserService;
import com.lucasmoraessilva.api_consumer.presentation.console.ConsolePrinter;
import com.lucasmoraessilva.api_consumer.presentation.file.FilePrinter;
import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiConsumerApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApiConsumerApplication.class, args);

        UserService userService = applicationContext.getBeanFactory().getBean(UserService.class);
        TaskService taskService = applicationContext.getBeanFactory().getBean(TaskService.class);

        List<UserEntity> users = userService.getAllUser();
        List<TaskEntity> tasks = taskService.getAllTasks();

        List<UserEntity> usersWithTasks = userService.mergeTasksWithUsers(users, tasks);

        ConsolePrinter.printUserData(usersWithTasks);
        FilePrinter.writeUsersData(usersWithTasks);
        FilePrinter.writeTasksData(extractTasks(usersWithTasks));

        applicationContext.close();
    }

    public static List<TaskEntity> extractTasks(List<UserEntity> usersWithTasks) {
        return usersWithTasks
                .stream()
                .map(user2 -> user2.getTasks().stream().toList())
                .collect(
                        () -> new ArrayList<>(),
                        (taskList, userTasks) -> taskList.addAll(userTasks),
                        (taskList1, taskList2) -> taskList1.addAll(taskList2));
    }
}
