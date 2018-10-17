package edu.uet.imu.dictIMU;

public class Word
{
    private String wordTarget;
    private String wordExplain;

    public Word(String wordTarget, String wordExplain)
    {
    	this.wordTarget  = wordTarget;
    	this.wordExplain = wordExplain;
    }

    public Word() {}

    public String getWordTarget()
    {
    	return this.wordTarget;
    }

    public void setWordTarget(String wordTarget)
    {
    	this.wordTarget = wordTarget;
    }

    public String getWordExplain()
    {
    	return this.wordExplain;
    }

    public void setWordExplain(String wordExplain)
    {
    	this.wordExplain = wordExplain;
    }
}
