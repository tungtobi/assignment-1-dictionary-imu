package edu.uet.imu.dictIMU;

import java.util.Scanner;

public class DictionaryCommandLine
{
	private DictionaryCommandline dictionaryCommandline;
	private DictionaryManagement dictionaryManager;

	public DictionaryCommandLine()
	{
		dictionaryCommandline = new DictionaryCommandline();
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
					dictionaryCommandline.showAllWords(dictionaryManager.getDictionary());
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