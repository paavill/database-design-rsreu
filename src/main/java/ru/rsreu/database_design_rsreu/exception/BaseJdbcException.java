package ru.rsreu.database_design_rsreu.exception;

public abstract class BaseJdbcException extends RuntimeException {
    public BaseJdbcException() {
        super();
    }

    public BaseJdbcException(String message) {
        super(message);
    }

    public BaseJdbcException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseJdbcException(Throwable cause) {
        super(cause);
    }

    protected BaseJdbcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
