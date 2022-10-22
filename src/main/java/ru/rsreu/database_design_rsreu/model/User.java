package ru.rsreu.database_design_rsreu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class User {
    private Long id;
    private UserRoleEnum userRoleEnum;
    private UserStatusEnum userStatusEnum;
    private String name;
}
