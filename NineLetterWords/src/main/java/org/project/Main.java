package org.project;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Application started at: " + new Date(startTime));

        try {
            DictionaryManager dictionaryManager = DictionaryManager.createDictionaryManager();

            WordsFinder wordsFinder = new WordsFinder(dictionaryManager);

            List<String> result = wordsFinder.findAllValidWords();
            long endTime = System.currentTimeMillis();
            System.out.println("Number of words: " + result.size());

            System.out.println("Application finished at: " + new Date(endTime));

        } catch (IOException e) {
            System.err.println("The dictionary could not be loaded.");
        }
    }
}