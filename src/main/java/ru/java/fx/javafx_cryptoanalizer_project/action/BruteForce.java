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

    public BruteForce(){
        this.readerFile = new ReaderFileImpl();
        this.writerFile = new WriterFileImpl();
    }
    public Result execute(String[] parameters){
        String encodedFile = parameters[0];
        String decodedFile = parameters[1];
        String exampleFile = parameters[2];
        int shift = bruteForce(encodedFile, exampleFile);
        List<String> encodedList = readerFile.readFile(encodedFile);
        List<String> decodedList = new ArrayList<>();
        for (int i = 0; i < encodedList.size(); i++) {
            decodedList.add(decode(encodedList.get(i), shift));
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
                String s = decode(encodedStr, shift);
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

    public String decode(String str, int shift) {
        StringBuilder newStr = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            String lowCase = str.toLowerCase();
            int indexOf = alphabet.indexOf(lowCase.charAt(i));
            // убираем лишний сдвиг, если он больше длины алфавита
            if (Math.abs(shift) > alphabet.length()) {
                shift %= alphabet.length();
            }
            if (indexOf != -1) {    // если символ есть в алфавите, то он шифруется
                indexOf += shift;
                if (indexOf >= alphabet.length()) {
                    indexOf %= alphabet.length();
                }else if(indexOf < 0){
                    if(Math.abs(indexOf) > alphabet.length()){
                        indexOf %= alphabet.length();
                    }
                    indexOf = alphabet.length() + indexOf;
                }
                if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z' ||
                        str.charAt(i) >= 'А' && str.charAt(i) <= 'Я') {
                    String upCaseStr = alphabet.toUpperCase();
                    newStr.append(upCaseStr.charAt(indexOf));
                } else {
                    newStr.append(alphabet.charAt(indexOf));
                }
            }else {                 // если символа нет в авфавите, то он не шифруется
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }
}
