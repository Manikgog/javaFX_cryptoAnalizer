package ru.java.fx.javafx_cryptoanalizer_project.action;

import ru.java.fx.javafx_cryptoanalizer_project.entity.Result;

import java.util.ArrayList;
import java.util.List;

import static ru.java.fx.javafx_cryptoanalizer_project.entity.Constants.alphabet;

public class Decoder implements Action {
    private final ReaderFile readerFile;
    private final WriterFile writerFile;

    public Decoder(){
        this.readerFile = new ReaderFileImpl();
        this.writerFile = new WriterFileImpl();
    }
    @Override
    public Result execute(String[] parameters) {
        String encodedFile = parameters[0];
        String decodedFile = parameters[1];
        List<String> encodedList = readerFile.readFile(encodedFile);
        List<String> decodedList = new ArrayList<>();
        int shift = Integer.parseInt(parameters[2]);
        for (int i = 0; i < encodedList.size(); i++) {
            decodedList.add(decode(encodedList.get(i), shift));
        }
        writerFile.writeFile(decodedFile, decodedList);
        return new Result("текст раскодирован в файле " + decodedFile);
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
            }else {                 // если символа нет в алфавите, то он не шифруется
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }
}
