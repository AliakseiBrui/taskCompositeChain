package com.epam.task3.exception;

public class LogicalExpressionException extends Exception {
    public LogicalExpressionException(){ }

    public LogicalExpressionException(String message){
        super(message);
    }

    public LogicalExpressionException(Throwable cause){
        super(cause);
    }

    public LogicalExpressionException(String message, Throwable cause){
        super(message,cause);
    }
}
