package ru.java.fx.javafx_cryptoanalizer_project.entity;

import java.io.File;

public class Constants {
    private final static String rus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private final static String eng = "abcdefghijklmnopqrstuvwxyz";
    private final static String digits = "0123456789";
    public final static String alphabet = rus + eng + digits;
    public final static String TXT_FOLDER = System.getProperty("user.dir")
            + File.separator + "text" + File.separator;
}
