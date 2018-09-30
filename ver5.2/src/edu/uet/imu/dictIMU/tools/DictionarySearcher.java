package edu.uet.imu.dictIMU.tools;

import java.util.ArrayList;

import edu.uet.imu.dictIMU.common.Word;
import javafx.collections.transformation.FilteredList;

public class DictionarySearcher
{
    public static ArrayList<Word> searcherForCommandline(String searchWord, ArrayList<Word> words)
    {
        if (searchWord == null || searchWord == "")
            return words;
        
        ArrayList<Word> res = new ArrayList<Word>();

        for (int i = 0; i < words.size(); i++)
        {
            if (words.get(i).getWordTarget().toLowerCase().contains(searchWord))
                res.add(words.get(i));
        }
        
        return res;
    }

    public static FilteredList<String> searcherForApplication(String oldVal, String newVal, FilteredList<String> word)
	{
		word.setPredicate(string -> string.toLowerCase().startsWith(newVal));
		return word;
	}

}