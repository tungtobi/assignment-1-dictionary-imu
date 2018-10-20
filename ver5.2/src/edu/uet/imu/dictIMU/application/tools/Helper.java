package edu.uet.imu.dictIMU.application.tools;

import edu.uet.imu.dictIMU.common.WordX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Helper
{
    public static String WordXToTextFX(WordX word)
    {
        String ret = "";
        if (!word.getPronunciation().equals(""))
            ret = ret + word.getPronunciation() + "\n";

        ret = ret + word.getWordExplain() + "\n\n";

        Map typeGroup = word.getTypeGroup();

        if (typeGroup != null && typeGroup.size() > 0)
        {
             ret = ret + "Những định nghĩa của " + word.getWordTarget() + "\n\n";


            for (Object o : typeGroup.entrySet())
            {
                Map.Entry pair = (Map.Entry) o;

                ret = ret + pair.getKey() + "\n";
                Map closeWord = (HashMap) pair.getValue();
                for (Object oo : closeWord.entrySet())
                {
                    Map.Entry pair1 = (Map.Entry) oo;
                    ret = ret + "\t" + pair1.getKey() + "\n\t\t";
                    ArrayList<String> words = (ArrayList<String>) pair1.getValue();
                    for (int j = 0; j < words.size() - 1; j++)
                    {
                        ret = ret + words.get(j) + ", ";
                    }

                    ret = ret + words.get(words.size() - 1) + "\n";
                }
            }
        }
        return ret;
    }
}
