package ru.rsreu.database_design_rsreu.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsreu.database_design_rsreu.exception.JdbcConnectionException;
import ru.rsreu.database_design_rsreu.exception.JdbcQueryExecutionException;
import ru.rsreu.database_design_rsreu.exception.JdbcQueryFormingException;
import ru.rsreu.database_design_rsreu.exception.JdbcResultMappingException;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTemplate implements JdbcOperations {
    private final Connection connection;

    @Autowired
    public JdbcTemplate(Connection connection) {
        this.connection = connection;
    }

    @PreDestroy
    public void destroy() throws SQLException {
        connection.close();
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
        ResultSet resultSet = executeDqlQuery(sql, params);
        return mapResultSet(resultSet, rowMapper);
    }

    @Override
    public int update(String sql, Object... params) {
        return executeDmlQuery(sql, params);
    }

    private int executeDmlQuery(String sql, Object... params) {
        PreparedStatement statement = getPreparedStatement(sql);
        prepareStatementWithParams(statement, params);
        try {
            return statement.executeUpdate();
        } catch (SQLException exception) {
            throw new JdbcQueryExecutionException("Ошибка при выполнении запроса", exception);
        }

    }

    private ResultSet executeDqlQuery(String sql, Object... params) {
        PreparedStatement statement = getPreparedStatement(sql);
        prepareStatementWithParams(statement, params);
        try {
            return statement.executeQuery();
        } catch (SQLException exception) {
            throw new JdbcQueryExecutionException("Ошибка при выполнении запроса", exception);
        }
    }

    private void prepareStatementWithParams(PreparedStatement statement, Object[] params) {
        try {
            for (int i = 1; i <= params.length; i++) {
                statement.setObject(i, params[i - 1]);
            }
        } catch (SQLException exception) {
            throw new JdbcQueryFormingException("Ошибка при фомирование добавлении параметра в запрос", exception);
        }
    }

    private PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException exception) {
            throw new JdbcConnectionException("Ошибка соединения с БД", exception);
        }
        return statement;
    }

    private <T> List<T> mapResultSet(ResultSet resultSet, RowMapper<T> rowMapper) {
        List<T> result = new ArrayList<>();
        try {
            for (int i = 0; resultSet.next(); i++) {
                result.add(rowMapper.mapRow(resultSet, i));
            }
        } catch (SQLException exception) {
            throw new JdbcResultMappingException("Ошибка при преобразовании ResultSet", exception);
        }
        return result;
    }
}
