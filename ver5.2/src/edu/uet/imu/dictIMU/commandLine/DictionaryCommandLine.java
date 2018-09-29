package edu.uet.imu.dictIMU.commandLine;

import edu.uet.imu.dictIMU.tools.DictionarySearcher;
import edu.uet.imu.dictIMU.common.Dictionary;
import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;

import java.util.Scanner;
import java.util.ArrayList;

public class DictionaryCommandLine
{
	private DictionaryManagement dictionaryManager;
    private Dictionary dictionary;

	public DictionaryCommandLine()
	{
		dictionaryManager = new DictionaryManagement();
        dictionary = dictionaryManager.getDictionary();
	}

	public static void showAllWords(ArrayList<Word> words)
	{
		System.out.printf("No    |English           |Vietnamese    %n");
		
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
					showAllWords(dictionary.getWords());
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
            System.out.println("4. Delete a word");
            System.out.println("5. Export dictionary to file");
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
                    ArrayList<Word> resultWords = DictionarySearcher.searcherForCommandline(searchWord.toLowerCase(), dictionary.getWords());
                    if (resultWords == null)
                        System.out.println("Word not found");
                    else 
                        showAllWords(resultWords);
                    break;
                case '4':
                    dictionaryManager.removeFromCommandline();
                    break;
                case '5':
                    dictionaryManager.dictionaryExportToFile("out/data/out.txt");
			}
			System.out.println("=========================");
		}
		while (c != '0');

    }


	public void runCommandline()
	{
		DictionaryCommandLine dictionary = new DictionaryCommandLine();
		dictionary.dictionaryAdvenced();
	}
}