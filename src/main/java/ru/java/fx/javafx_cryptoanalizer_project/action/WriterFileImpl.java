package ru.java.fx.javafx_cryptoanalizer_project.action;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WriterFileImpl implements WriterFile{
    public void writeFile(String dst, List<String> allLines){
        try{
            Files.write(Paths.get(dst), allLines, Charset.forName("windows-1251"));
        }
        catch (IOException ex) {
            throw new RuntimeException("Ошибка ввода-вывода " + ex);
        }
    }
}
