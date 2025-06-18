package org.project;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class WordsFinder {
    private final DictionaryManager dictionaryManager;
    private final Set<String> validWords;

    public WordsFinder(DictionaryManager dictionaryManager) {
        this.dictionaryManager = dictionaryManager;
        this.validWords = new HashSet<>();
    }

    /**
     * Generates valid words generation by generation, starting from the words "A" and "I",
     * and every generation is generated from the previous with wors that are 1 letter longer
     */
    public List<String> findAllValidWords() {
        Set<String> currentGeneration = new HashSet<>();
        currentGeneration.add("A");
        currentGeneration.add("I");

        validWords.addAll(currentGeneration);

        for (int wordLength = 2; wordLength <= 9; wordLength++) {
            currentGeneration = buildNextGeneration(currentGeneration, wordLength);
            validWords.addAll(currentGeneration);

        }

        return currentGeneration.stream().filter(word -> word.length() == 9).toList();
    }

    /**
     * Builds the next generation of words from the previous one. For each word in the
     * previous generation, it gets from the childrenParentMap all words that are one letter longer
     * and can be formed from the current word.
     */
    private Set<String> buildNextGeneration(Set<String> previousGeneration, int currentLength) {
        Set<String> nextGenerationWords = new HashSet<>();
        for (String word : previousGeneration) {
            Set<String> children = dictionaryManager.getChildrenWords(word);
            nextGenerationWords.addAll(children);
        }
        return nextGenerationWords;
    }
}
