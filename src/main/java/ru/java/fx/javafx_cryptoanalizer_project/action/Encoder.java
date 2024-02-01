package ru.java.fx.javafx_cryptoanalizer_project.action;

import ru.java.fx.javafx_cryptoanalizer_project.entity.Result;

import java.util.ArrayList;
import java.util.List;

import static ru.java.fx.javafx_cryptoanalizer_project.entity.Constants.alphabet;

public class Encoder implements Action {
    private final ReaderFile readerFile;
    private final WriterFile writerFile;

    public Encoder(){
        this.readerFile = new ReaderFileImpl();
        this.writerFile = new WriterFileImpl();
    }
    @Override
    public Result execute(String[] parameters) {
        String encodedFile = parameters[1];
        String sourceFile = parameters[0];
        List<String> list = readerFile.readFile(sourceFile);
        List<String> encodedList = new ArrayList<>();
        int shift = Integer.parseInt(parameters[2]);
        for (int i = 0; i < list.size(); i++) {
            encodedList.add(encode(list.get(i), shift));
        }
        writerFile.writeFile(encodedFile, encodedList);
        return new Result("текст закодирован в файле " + encodedFile);
    }

    public String encode(String str, int shift) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {

            String lowCase = str.toLowerCase();
            int indexOf = alphabet.indexOf(lowCase.charAt(i));
            if (Math.abs(shift) > alphabet.length()) {
                shift %= alphabet.length();
            }
            if (indexOf != -1) {
                indexOf += shift;

                if (indexOf >= alphabet.length()) {
                    indexOf %= alphabet.length();
                }
                else if(indexOf < 0){
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
            }else {
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }
}
