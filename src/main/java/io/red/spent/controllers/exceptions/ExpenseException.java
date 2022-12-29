package io.red.spent.controllers.exceptions;

public class ExpenseException extends RuntimeException{
    public ExpenseException(String message){
        super(message);
    }
}
