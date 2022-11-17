package ru.rsreu.database_design_rsreu.jdbc;

import java.util.List;

public interface JdbcOperations {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params);

    int update(String sql, Object... params);
}
