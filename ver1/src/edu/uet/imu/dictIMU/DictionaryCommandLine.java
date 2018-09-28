package edu.uet.imu.dictIMU;

import java.util.Scanner;
import java.util.ArrayList;

public class DictionaryCommandLine
{
	private DictionaryManagement dictionaryManager;

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

	public DictionaryCommandLine()
	{
		dictionaryManager = new DictionaryManagement();
	}

	public void dictionaryBasic()
	{
		Scanner scanner = new Scanner(System.in);
		char c = ' ';
		do 
		{
			System.out.println("1. Them Tu Moi");
			System.out.println("2. Hien tat ca cac tu");
			System.out.println("0. Thoat");
			System.out.println("=========================");
			System.out.print("Lua chon: ");
			c = scanner.next().charAt(0);
		  	switch (c)
		  	{
		  		case '1':
					dictionaryManager.insertFromCommandline();
		  			break;
		 		case '2':
					showAllWords(dictionaryManager.getDictionary());
					break;
			}
			System.out.println("=========================");
		}
		while (c != '0');
	}

	public static void main(String[] args)
	{
		DictionaryCommandLine dictionary = new DictionaryCommandLine();
		dictionary.dictionaryBasic();
	}
}