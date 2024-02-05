package ru.java.fx.javafx_cryptoanalizer_project.action;

import ru.java.fx.javafx_cryptoanalizer_project.entity.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.java.fx.javafx_cryptoanalizer_project.entity.Constants.alphabet;

public class Encoder implements Action {
    private final ReaderFile readerFile;
    private final WriterFile writerFile;

    private final Map<Character, Integer> indexByChar;
    private final Map<Integer, Character> charByIndex;

    public Encoder() {
        this.readerFile = new ReaderFileImpl();
        this.writerFile = new WriterFileImpl();
        this.indexByChar = new HashMap<>();
        this.charByIndex = new HashMap<>();
        for (int i = 0; i < alphabet.length(); i++) {
            indexByChar.put(alphabet.charAt(i), i);
            charByIndex.put(i, alphabet.charAt(i));
        }
    }

    @Override
    public Result execute(String[] parameters) {
        String encodedFile = parameters[1];
        String sourceFile = parameters[0];
        List<String> list = readerFile.readFile(sourceFile);
        List<String> encodedList = new ArrayList<>();
        int shift = Integer.valueOf(parameters[2]);
        for (int i = 0; i < list.size(); i++) {
            encodedList.add(encode(list.get(i), shift));
        }
        writerFile.writeFile(encodedFile, encodedList);
        return new Result("текст закодирован в файле " + encodedFile);
    }

    public String encode(String str, int shift) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            Character c = (str.charAt(i) + "").toLowerCase().charAt(0);
            if (indexByChar.containsKey(c)) {
                int indexOf = indexByChar.get(c);
                if (Math.abs(shift) > alphabet.length()) {
                    shift %= alphabet.length();
                }
                indexOf += shift;
                if (indexOf >= alphabet.length()) {
                    indexOf %= alphabet.length();
                } else if (indexOf < 0) {
                    if (Math.abs(indexOf) > alphabet.length()) {
                        indexOf %= alphabet.length();
                    }
                    indexOf = alphabet.length() + indexOf;
                }
                if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z' ||
                        str.charAt(i) >= 'А' && str.charAt(i) <= 'Я') {
                    newStr.append(charByIndex.get(indexOf).toString().toUpperCase());
                } else {
                    newStr.append(charByIndex.get(indexOf));
                }
            } else {
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }
}

