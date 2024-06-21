package com.lucasmoraessilva.api_consumer.business.entity;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private Integer id;
    private String name;
    private String email;
    private Set<TaskEntity> tasks;

}
