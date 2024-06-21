package com.lucasmoraessilva.api_consumer.presentation.file;

import com.lucasmoraessilva.api_consumer.business.entity.TaskEntity;
import com.lucasmoraessilva.api_consumer.business.entity.UserEntity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FilePrinter {

    public static void writeUsersData(List<UserEntity> users) throws IOException {
        try (FileWriter fw = new FileWriter("./users.txt")) {
            users.forEach(user -> {
                try {
                    fw.write(String.format("%d;%s;%s;\n", user.getId(), user.getName(), user.getEmail()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void writeTasksData(List<TaskEntity> tasks) throws IOException {
        try (FileWriter fw = new FileWriter("./tasks.txt")) {
            tasks.forEach(task -> {
                try {
                    fw.write(String.format("%d;%d;%s;%s;\n", task.getId(), task.getResponsible().getId(), task.getTitle(), task.isCompleted()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
