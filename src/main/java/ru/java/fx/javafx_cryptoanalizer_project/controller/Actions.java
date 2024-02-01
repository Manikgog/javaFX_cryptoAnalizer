package ru.java.fx.javafx_cryptoanalizer_project.controller;

import ru.java.fx.javafx_cryptoanalizer_project.action.Action;
import ru.java.fx.javafx_cryptoanalizer_project.action.BruteForce;
import ru.java.fx.javafx_cryptoanalizer_project.action.Decoder;
import ru.java.fx.javafx_cryptoanalizer_project.action.Encoder;
import ru.java.fx.javafx_cryptoanalizer_project.exceptions.ArgumentsListException;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTEFORCE(new BruteForce());

    private final Action action;

    Actions(Action action){
        this.action = action;
    }

    public static Action find(String actionName){
        try {
            Actions value = Actions.valueOf(actionName.toUpperCase());
            Action action = value.action;
            return action;
        }catch (IllegalArgumentException e){
            throw new ArgumentsListException("Не найдена указанная команда.");
        }
    }

}
