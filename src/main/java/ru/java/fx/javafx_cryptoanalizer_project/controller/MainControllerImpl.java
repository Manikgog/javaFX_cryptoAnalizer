package ru.java.fx.javafx_cryptoanalizer_project.controller;

import ru.java.fx.javafx_cryptoanalizer_project.action.Action;
import ru.java.fx.javafx_cryptoanalizer_project.entity.Result;
import ru.java.fx.javafx_cryptoanalizer_project.exceptions.ArgumentsListException;

public class MainControllerImpl implements MainController{
    public MainControllerImpl(){
    }
    public Result doAction(String actionName, String[] args){
        if(args.length < 3){
            throw new ArgumentsListException("Ошибка. Задано мало аргументов.");
        }else if(args.length > 3){
            throw new ArgumentsListException("Ошибка. Задано слишком много аргументов.");
        }
        Action action = Actions.find(actionName);
        Result result = action.execute(args);
        return result;
    }
}
