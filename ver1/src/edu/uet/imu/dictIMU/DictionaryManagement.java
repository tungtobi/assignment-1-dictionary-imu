package edu.uet.imu.dictIMU;

import java.util.Scanner;

public class DictionaryManagement
{
	private Dictionary dictionary;

	public DictionaryManagement()
	{
		dictionary = new Dictionary();
	}

	public Dictionary getDictionary()
	{
		return this.dictionary;
	}

	public void insertFromCommandline()
	{
		Scanner scanner = new Scanner(System.in);
		
		String eng;
		String vi;

		System.out.print("- Enter the English word: ");
		eng = scanner.nextLine();
		
		System.out.print("- Enter the explain word: ");
		vi = scanner.nextLine();

		Word word = new Word(eng, vi);
		dictionary.addWord(word);
	}
}

