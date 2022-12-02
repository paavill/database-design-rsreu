package ru.rsreu.database_design_rsreu.model;

import java.util.HashMap;
import java.util.Map;

public enum UserRoleEnum {
    ADMIN(1),
    USER(2);

    private static final Map<Integer, UserRoleEnum> rolesById = Map.of(
            ADMIN.id, ADMIN,
            USER.id, USER
    );

    public static UserRoleEnum getRoleById(int id) {
        return rolesById.get(id);
    }

    private final int id;

    public int getId() {
        return id;
    }

    UserRoleEnum(int id) {
        this.id = id;
    }
}
