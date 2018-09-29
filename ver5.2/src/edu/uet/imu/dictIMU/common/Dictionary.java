package edu.uet.imu.dictIMU.common;

import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        if (word == null)
            return ;

        word.setWordTarget(word.getWordTarget().toLowerCase());
        word.setWordExplain(word.getWordExplain().toLowerCase());

        if (!wordsIndex.contains(word.getWordTarget()))
        {
            int index = 0;
            for (; index < words.size(); index++)
            {
                if (words.get(index).compareTo(word) > 0)
                    break;
            }
         	words.add(index, word);
            wordsIndex.add(index, word.getWordTarget());
        }
    }

    public void removeWord(Word word)
    {
        if (word == null)
            return;
        
        word.setWordTarget(word.getWordTarget().toLowerCase());
        word.setWordExplain(word.getWordExplain().toLowerCase());

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

    public ObservableList<String> getObservableWordsIndexList()
	{
        ObservableList<String> res = FXCollections.observableArrayList(this.wordsIndex);
        return res;
	}

    public void sortList()
    {
        Collections.sort(words);
        Collections.sort(wordsIndex);
    }
}
