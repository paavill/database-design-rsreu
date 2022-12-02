package ru.rsreu.database_design_rsreu.model;

import java.util.Map;

public enum UserStatusEnum {
    OFFLINE(1),
    ONLINE(2);

    private static final Map<Integer, UserStatusEnum> statuesById = Map.of(
            OFFLINE.id, OFFLINE,
            ONLINE.id, ONLINE
    );

    public static UserStatusEnum getStatusById(int id) {
        return statuesById.get(id);
    }

    public int getId() {
        return id;
    }

    private final int id;

    UserStatusEnum(int id) {
        this.id = id;
    }
}
