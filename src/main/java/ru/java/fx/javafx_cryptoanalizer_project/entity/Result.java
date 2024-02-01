package ru.java.fx.javafx_cryptoanalizer_project.entity;

public class Result {
    private final String message;
    public Result(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
