package edu.uet.imu.dictIMU;

import java.util.Scanner;
import java.util.ArrayList;

public class DictionaryCommandLine
{
	private DictionaryManagement dictionaryManager;

	public DictionaryCommandLine()
	{
		dictionaryManager = new DictionaryManagement();
	}

	public static void showAllWords(ArrayList<Word> words)
	{
		System.out.printf("No    |English             |Vietnamese    %n");
		
		int length = words.size();

		for (int i = 0; i < length; i++)
		{
			System.out.printf("%-6d|%-18s|%s%n", i + 1, words.get(i).getWordTarget(), words.get(i).getWordExplain());
		}
	}

	public void dictionaryBasic()
	{
		Scanner scanner = new Scanner(System.in);
		char c = ' ';
		do 
		{
			System.out.println("1. Add new word");
			System.out.println("2. Show all words");
			System.out.println("0. Quit");
			System.out.println("=========================");
			System.out.print("Option: ");
			c = scanner.next().charAt(0);
		  	switch (c)
		  	{
		  		case '1':
					dictionaryManager.insertFromCommandline();
		  			break;
		 		case '2':
					showAllWords(dictionaryManager.getDictionary().getWords());
					break;
			}
			System.out.println("=========================");
		}
		while (c != '0');
	}

    public void dictionaryAdvenced()
    {
        Scanner scanner = new Scanner(System.in);
		char c = ' ';
		do 
		{
			System.out.println("1. Add new words from File");
			System.out.println("2. Show all words");
            System.out.println("3. Search a word");
			System.out.println("0. Quit");
			System.out.println("=========================");
			System.out.print("Option: ");
			c = scanner.next().charAt(0);
		  	switch (c)
		  	{
		  		case '1':
					dictionaryManager.insertFromFile("resources/data/dictionary.txt");
		  			break;
		 		case '2':
					showAllWords(dictionaryManager.getDictionary().getWords());
					break;
                case '3':
                    System.out.print("Enter a word: ");
                    String searchWord = scanner.next();
                    ArrayList<Word> resultList = dictionaryManager.dictionaryLookup(searchWord.toLowerCase());
                    showAllWords(resultList);
			}
			System.out.println("=========================");
		}
		while (c != '0');

    }

	public static void main(String[] args)
	{
		DictionaryCommandLine dictionary = new DictionaryCommandLine();
		dictionary.dictionaryAdvenced();
	}
}
