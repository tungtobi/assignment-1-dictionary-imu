package edu.uet.imu.dictIMU;

import java.util.ArrayList;
import java.util.Collections;

public class Dictionary
{
    private ArrayList<Word> words;
    private ArrayList<String> wordsIndex;

    public Dictionary()
    {
    	words = new ArrayList<Word>();
        wordsIndex = new ArrayList<String>();
    }

    public void addWord(Word word)
    {
        if (!wordsIndex.contains(word.getWordTarget()))
        {
         	words.add(word);
            wordsIndex.add(word.getWordTarget());
        }
    }

    public void removeWord(Word word)
    {
        if (wordsIndex.contains(word.getWordTarget()))
        {
            words.remove(word);
            wordsIndex.remove(word.getWordTarget());
        }
    }

    public ArrayList<Word> getWords()
    {
    	return this.words;
    }

    public ArrayList<String> getWordsIndex()
    {
        return this.wordsIndex;
    }

    public void sortList()
    {
        Collections.sort(words);
        Collections.sort(wordsIndex);
    }
}
