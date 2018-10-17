package edu.uet.imu.dictIMU.common;

public class Word implements Comparable<Word>
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

    @Override
    public int compareTo(Word word)
    {
        String wordTarget = word.getWordTarget().toLowerCase();
        return this.wordTarget.toLowerCase().compareTo(wordTarget);
    }

    @Override 
    public String toString()
    {
        return "[wordTarget: " + this.wordTarget + ", wordExplain: " + this.wordExplain + "]";
    }
}
