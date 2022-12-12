package ru.rsreu.database_design_rsreu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsreu.database_design_rsreu.jdbc.JdbcTemplate;
import ru.rsreu.database_design_rsreu.model.User;
import ru.rsreu.database_design_rsreu.model.UserRoleEnum;
import ru.rsreu.database_design_rsreu.model.UserStatusEnum;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Component
public class UserRepository {
    private static final String STORED_INSERT = "CALL insert_user(?)";
    private static final String STORED_COUNT_ALL = "CALL count_users(?) ";
    private static final String STORED_COUNT_ONLINE = "{ ? = CALL count_user_with_online_status() }";

    private final JdbcTemplate jdbcTemplate;

    private final Connection connection;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, Connection connection) {
        this.jdbcTemplate = jdbcTemplate;
        this.connection = connection;
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

    public void insert(String username) {
        try (CallableStatement statement = connection.prepareCall(STORED_INSERT)) {
            statement.setString(1, username);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Exception in call insert_user stored procedure: " + e.getMessage());
        }
    }

    public int getCount() {
        try (CallableStatement statement = connection.prepareCall(STORED_COUNT_ALL)) {
            statement.setInt(1, 0);
            statement.registerOutParameter(1, Types.INTEGER);
            statement.execute();
            return statement.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Exception in call count_users stored procedure: " + e.getMessage());
        }
    }

    public int getOnlineCount() {
        try (CallableStatement statement = connection.prepareCall(STORED_COUNT_ONLINE)) {
            statement.registerOutParameter(1, Types.INTEGER);
            statement.execute();
            return statement.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Exception in call count_user_with_online_status stored procedure: " + e.getMessage());
        }
    }
}
