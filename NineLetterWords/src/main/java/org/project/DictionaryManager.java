package org.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

class DictionaryManager {
    private static final String DICTIONARY_URL = "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";

    private final Set<String> dictionary;
    private final Map<String, Set<String>> childrenLookupMap;


    private DictionaryManager(Set<String> dictionary, Map<String, Set<String>> childrenLookupMap) {
        this.dictionary = dictionary;
        this.childrenLookupMap = childrenLookupMap;
    }

    /**
     * Load the dictionary, filter the words in it and creates childrenParentsMap
     */
    public static DictionaryManager createDictionaryManager() throws IOException {
        Set<String> rawDictionary = loadDictionary();
        long startTimeAfterLoading = System.currentTimeMillis();

        System.out.println("Start after loading the dictionary: " + new Date(startTimeAfterLoading));

        Set<String> filteredDictionary = rawDictionary.stream()
                .filter(w -> w.contains("I") || w.contains("A"))
                .filter(w -> w.length() <= 9)
                .map(String::toUpperCase)
                .collect(Collectors.toUnmodifiableSet());

        Map<String, Set<String>> childrenParentWordsMap = createChildrenParentsMap(filteredDictionary);

        return new DictionaryManager(filteredDictionary, childrenParentWordsMap);
    }

    /**
     * Loads all english words from the URL
     */
    private static Set<String> loadDictionary() throws IOException {
        URL wordsUrl = new URL(DICTIONARY_URL);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()))) {
            return br.lines()
                    .skip(2)
                    .collect(Collectors.toSet());
        }
    }

    /**
     * Creates map with key a word - parent  and value list of all words 1 letter longer - children
     */
    private static Map<String, Set<String>> createChildrenParentsMap(Set<String> dictionaryWords) {
        Map<String, Set<String>> lookupMap = new HashMap<>();
        for (String word : dictionaryWords) {
            for (int i = 0; i < word.length(); i++) {
                String shorterWord = word.substring(0, i) + word.substring(i + 1);
                lookupMap.computeIfAbsent(shorterWord, k -> new HashSet<>()).add(word);
            }
        }
        return lookupMap;
    }

    /**
     * Return all children words(words that are 1 letter longer and can be created from this word)
     */
    public Set<String> getChildrenWords(String word) {
        return childrenLookupMap.getOrDefault(word, Collections.emptySet());
    }


    public boolean contains(String word) {
        return dictionary.contains(word);
    }
}