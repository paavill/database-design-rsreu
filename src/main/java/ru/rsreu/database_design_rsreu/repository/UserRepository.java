package ru.rsreu.database_design_rsreu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.rsreu.database_design_rsreu.model.User;
import ru.rsreu.database_design_rsreu.model.UserRoleEnum;
import ru.rsreu.database_design_rsreu.model.UserStatusEnum;

import java.util.List;
@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return jdbcTemplate.query("select * from system_user", (rs, rowNum) -> new User(rs.getLong(1), Enum.valueOf(UserRoleEnum.class, rs.getString(2)), Enum.valueOf(UserStatusEnum.class, rs.getString(3)), rs.getString(4)));
    }

    public void save(User user) {
        jdbcTemplate.update("insert into system_user values (default, ?, ?, ?)", user.getUserRoleEnum().toString(),user.getUserStatusEnum().toString(), user.getName());
    }
}
