package com.epam.task3.exception;

public class WrongCompositeTypeException extends Exception {
    public WrongCompositeTypeException(Throwable cause){
        super(cause);
    }

    public WrongCompositeTypeException(String message){
        super(message);
    }

    public WrongCompositeTypeException(String message, Throwable cause){
        super(message,cause);
    }
}
