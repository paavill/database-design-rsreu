package ru.rsreu.database_design_rsreu.exception;

public class JdbcQueryExecutionException extends BaseJdbcException {
    public JdbcQueryExecutionException(String message) {
        super(message);
    }

    public JdbcQueryExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
