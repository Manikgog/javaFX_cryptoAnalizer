package ru.java.fx.javafx_cryptoanalizer_project.exceptions;

public class ArgumentsListException extends IllegalArgumentException{
    public ArgumentsListException() {
    }

    public ArgumentsListException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
