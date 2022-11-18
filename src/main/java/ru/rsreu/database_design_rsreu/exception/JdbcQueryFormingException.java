package ru.rsreu.database_design_rsreu.exception;

public class JdbcQueryFormingException extends BaseJdbcException {
    public JdbcQueryFormingException(String message) {
        super(message);
    }

    public JdbcQueryFormingException(String message, Throwable cause) {
        super(message, cause);
    }
}
