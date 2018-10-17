package edu.uet.imu.dictIMU;

import java.util.ArrayList;

public class Dictionary
{
    private ArrayList<Word> words;

    public Dictionary()
    {
    	words = new ArrayList<Word>();
    }

    public void addWord(Word word)
    {
    	words.add(word);
    }

    public ArrayList<Word> getWords()
    {
    	return this.words;
    }
}
