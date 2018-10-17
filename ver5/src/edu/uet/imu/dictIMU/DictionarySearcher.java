package edu.uet.imu.dictIMU;

import java.util.ArrayList;
import javafx.collections.transformation.FilteredList;

public class DictionarySearcher
{
    public static ArrayList<Word> searcherForCommandline(String searchWord, ArrayList<Word> dict)
    {
        if (searchWord == null || searchWord == "")
            return dict;
        
        ArrayList<Word> res = new ArrayList<Word>();

        for (int i = 0; i < dict.size(); i++)
        {
            if (dict.get(i).getWordTarget().toLowerCase().contains(searchWord))
                res.add(dict.get(i));
        }
        
        return res;
    }

    public static FilteredList<String> searcherForApplication(String oldVal, String newVal, FilteredList<String> orginal)
	{
		orginal.setPredicate(string -> string.toLowerCase().startsWith(newVal));
		return orginal;
	}

}
