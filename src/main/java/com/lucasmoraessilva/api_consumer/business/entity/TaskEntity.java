package com.lucasmoraessilva.api_consumer.business.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {

    private Integer id;
    private UserEntity responsible;
    private String title;
    private boolean completed;

}
