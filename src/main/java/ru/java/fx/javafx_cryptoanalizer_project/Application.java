package ru.java.fx.javafx_cryptoanalizer_project;

import ru.java.fx.javafx_cryptoanalizer_project.controller.MainController;
import ru.java.fx.javafx_cryptoanalizer_project.controller.MainControllerImpl;
import ru.java.fx.javafx_cryptoanalizer_project.entity.Result;

import java.util.Arrays;

public class Application {
    private final MainController mainController;
    public Application() {
        this.mainController = new MainControllerImpl();
    }

    public Result run(String[] args){
        if(args.length > 0) {
            String action = args[0];
            String[] arguments = Arrays.copyOfRange(args, 1, args.length);
            return mainController.doAction(action, arguments);
        }
        throw new IllegalArgumentException("не введены аргументы командной строки");
    }
}
