package ru.java.fx.javafx_cryptoanalizer_project.action;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReaderFileImpl implements ReaderFile{
    public List<String> readFile(String src) {

        List<String> list = new ArrayList<>();
        try(FileReader in = new FileReader(src, Charset.forName("windows-1251"));
            BufferedReader reader = new BufferedReader(in))
        {
            while (reader.ready())
            {
                String line = reader.readLine();
                list.add(line);
            }
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("Ошибка указания пути " + e);
        }catch (IOException e){
            throw new RuntimeException("Ошибка ввода-вывода " + e);
        }
        return list;
    }

}
