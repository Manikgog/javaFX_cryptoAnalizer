package ru.java.fx.javafx_cryptoanalizer_project.action;

import ru.java.fx.javafx_cryptoanalizer_project.entity.Result;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static ru.java.fx.javafx_cryptoanalizer_project.entity.Constants.alphabet;

public class BruteForce implements Action{
    private final ReaderFile readerFile;
    private final WriterFile writerFile;
    private final Decoder decoder;

    public BruteForce(){
        this.readerFile = new ReaderFileImpl();
        this.writerFile = new WriterFileImpl();
        this.decoder = new Decoder();
    }
    public Result execute(String[] parameters){
        String encodedFile = parameters[0];
        String decodedFile = parameters[1];
        String exampleFile = parameters[2];
        int shift = bruteForce(encodedFile, exampleFile);
        List<String> encodedList = readerFile.readFile(encodedFile);
        List<String> decodedList = new ArrayList<>();
        for (int i = 0; i < encodedList.size(); i++) {
            decodedList.add(decoder.decode(encodedList.get(i), shift));
        }
        writerFile.writeFile(parameters[1], decodedList);
        return new Result("текст раскодирован в файле " + decodedFile);
    }

    public int bruteForce(String src, String example){
        Set<String> exampleWords = splitToWords(example);
        Set<String> srcWords = splitToWords(src);
        int count;
        int shift;
        for (shift = 0; shift < alphabet.length(); shift++) {
            count = 0;
            for (String encodedStr : srcWords) {
                String s = decoder.decode(encodedStr, shift);
                if(exampleWords.contains(s)){
                    count++;
                }
            }
            if(count > 100){
                break;
            }
        }
        return shift;
    }
    public Set<String> splitToWords(String path){
        Set<String> list = new TreeSet<>();
        try(FileReader in = new FileReader(path, Charset.forName("windows-1251"));
            BufferedReader reader = new BufferedReader(in))
        {
            while (reader.ready())
            {
                String line = reader.readLine();
                String[] parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if(parts[i].isEmpty()
                            || parts[i].contains(",")
                            || parts[i].contains(".")
                            || parts[i].contains("- ")
                            || parts[i].contains(" -")
                            || parts[i].contains(")")
                            || parts[i].contains("(")
                            || parts[i].contains("[")
                            || parts[i].contains("]")
                    ){
                        continue;
                    }
                    list.add(parts[i]);
                }
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
