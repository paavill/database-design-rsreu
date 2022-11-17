package ru.rsreu.database_design_rsreu.exception;

public class JdbcConnectionException extends BaseJdbcException {
    public JdbcConnectionException(String message) {
        super(message);
    }

    public JdbcConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
