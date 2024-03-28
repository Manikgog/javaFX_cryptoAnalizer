package ru.java.fx.javafx_cryptoanalizer_project.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ReaderFileImpl implements ReaderFile{
    public List<String> readFile(String src) {
        List<String> allLines;
        try(Stream<String> stream = Files.readAllLines(Paths.get(src), Charset.forName("windows-1251"))
                .stream()){
            allLines = stream.toList();
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("Ошибка указания пути " + e);
        }catch (IOException e){
            throw new RuntimeException("Ошибка ввода-вывода " + e);
        }
        return allLines;
    }

}
