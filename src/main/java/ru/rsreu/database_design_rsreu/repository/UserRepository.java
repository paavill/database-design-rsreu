package ru.rsreu.database_design_rsreu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsreu.database_design_rsreu.jdbc.JdbcTemplate;
import ru.rsreu.database_design_rsreu.model.User;
import ru.rsreu.database_design_rsreu.model.UserRoleEnum;
import ru.rsreu.database_design_rsreu.model.UserStatusEnum;

import java.util.List;

@Component
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query("select * from system_user",
                (rs, rowNum) ->
                        new User(
                                rs.getLong(1),
                                Enum.valueOf(UserRoleEnum.class, rs.getString(2)),
                                Enum.valueOf(UserStatusEnum.class, rs.getString(3)),
                                rs.getString(4)
                        ));
    }

    public void save(User user) {
        jdbcTemplate.update("insert into system_user values (default, ?, ?, ?)",
                user.getUserRoleEnum().toString(), user.getUserStatusEnum().toString(), user.getName());
    }
}
