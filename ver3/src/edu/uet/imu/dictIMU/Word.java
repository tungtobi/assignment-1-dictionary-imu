package edu.uet.imu.dictIMU;

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
        String wordTarget = word.getWordTarget();
        return this.wordTarget.compareTo(wordTarget);
    }

    @Override 
    public String toString()
    {
        return "[wordTarget: " + this.wordTarget + ", wordExplain: " + this.wordExplain + "]";
    }
}
