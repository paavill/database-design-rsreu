package ru.rsreu.database_design_rsreu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsreu.database_design_rsreu.jdbc.JdbcTemplate;
import ru.rsreu.database_design_rsreu.model.User;
import ru.rsreu.database_design_rsreu.model.UserRoleEnum;
import ru.rsreu.database_design_rsreu.model.UserStatusEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static User mapUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong(1),
                UserRoleEnum.getRoleById(rs.getInt(2)),
                UserStatusEnum.getStatusById(rs.getInt(3)),
                rs.getString(4)
        );
    }

    public List<User> findByName(String name) {
        return jdbcTemplate.query("select * from system_user where name = ?",
                (set, rowNumber) -> mapUser(set), name);
    }

    public List<User> findAll() {
        return jdbcTemplate.query("select * from system_user",
                (rs, rowNum) ->
                        mapUser(rs));
    }

    public void save(User user) {
        jdbcTemplate.update("insert into system_user values (default, ?, ?, ?)",
                user.getUserRoleEnum().getId(), user.getUserStatusEnum().getId(), user.getName());
    }
}
