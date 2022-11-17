package ru.rsreu.database_design_rsreu.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(ResultSet set, int rowNumber) throws SQLException;
}
