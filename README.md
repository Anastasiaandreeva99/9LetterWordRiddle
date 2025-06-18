# 9LetterWordRiddle

This is a solution of the 9 letter riddle in java. 
My solution is first to filter the words from the dictionary to be with length between 1 and 9 letters and to have either the letter A or I in it.
After filtering the dictionary I create a map in which  the keys are words and the values are list of words that are one letter longer than their key, the words are in the dictionary and are possible to be created from the key. 

I create this map by itterating through the filtered dictionary and for every word I generate all words with one letter shorter, by removing every possible letter and put them in the map like that:
shorterWord - key;
longerWord - in the list with values 
{shorterW, [longerW1, longerWord2, ... , newLongerW]}
Like that in the map all values are valid words, because they come from the dictionary.

Then I start generating my words from one letter words which are A and I and I generate new generations with longer words using the previous one, this happens by itterating the words from the given generation and for each of them i lookup in the childrenParentsMap and get all words that are one character longer and are from the dictionary.


WIth this dictionary that i have the result is 775 words.
The time is ~1s without loading the data and ~2,5 s with loading it.
Possible optimization is when creating the map not to generate shorter words(keys) that do not have A or I.
