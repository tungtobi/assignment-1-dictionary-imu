package edu.uet.imu.dictIMU.common;

import java.util.ArrayList;
import java.util.Map;

public class WordX extends Word
{
    private String pronunciation;
    private Map<String, Map<String, ArrayList<String>>> typeGroup;

    public WordX()
    {
    }

    public WordX(String wordTarget, String wordExplain, String pronunciation, Map<String, Map<String, ArrayList<String>>> typeGroup)
    {
        super(wordTarget, wordExplain);
        this.pronunciation = pronunciation;
        this.typeGroup = typeGroup;
    }

    public WordX(String pronunciation, Map<String, Map<String, ArrayList<String>>> typeGroup)
    {
        this.pronunciation = pronunciation;
        this.typeGroup = typeGroup;
    }

    public String getPronunciation()
    {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation)
    {
        this.pronunciation = pronunciation;
    }

    public Map getTypeGroup() {
        return typeGroup;
    }

    public void setTypeGroup(Map<String, Map<String, ArrayList<String>>> typeGroup) {
        this.typeGroup = typeGroup;
    }

    @Override
    public String toString()
    {
        return "[wordTarget: " + this.getWordTarget() +
                ", wordExplain: " + this.getWordExplain() +
                ", pronunciation: " + this.pronunciation +
                "typeGroup: " + typeGroup.toString() + "]";
    }
}
