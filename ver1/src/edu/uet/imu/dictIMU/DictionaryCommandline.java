package edu.uet.imu.dictIMU;

import java.util.ArrayList;

public class DictionaryCommandline
{

	public void showAllWords(Dictionary dictionary)
	{
		System.out.printf("No    |English    |Vietnamese    %n");
		
		ArrayList<Word> words = dictionary.getWords();
		int length = words.size();

		for (int i = 0; i < length; i++)
		{
			System.out.printf("%-6d|%-11s|%s%n", i + 1, words.get(i).getWordTarget(), words.get(i).getWordExplain());
		}
	}
}