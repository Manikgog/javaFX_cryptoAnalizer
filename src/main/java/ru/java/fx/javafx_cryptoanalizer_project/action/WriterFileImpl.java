package ru.java.fx.javafx_cryptoanalizer_project.action;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class WriterFileImpl implements WriterFile{
    public void writeFile(String dst, List<String> encodedList){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dst, Charset.forName("windows-1251")))) {
            for (int i = 0; i < encodedList.size(); i++) {
                bufferedWriter.append(encodedList.get(i));
                bufferedWriter.append("\n");
            }
            bufferedWriter.flush();
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка ввода-вывода " + ex);
        }
    }
}
