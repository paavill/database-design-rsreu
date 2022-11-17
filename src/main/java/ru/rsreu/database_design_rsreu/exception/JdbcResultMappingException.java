package ru.rsreu.database_design_rsreu.exception;

public class JdbcResultMappingException extends BaseJdbcException {
    public JdbcResultMappingException(String message) {
        super(message);
    }

    public JdbcResultMappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
