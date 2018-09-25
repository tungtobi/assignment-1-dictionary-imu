package edu.uet.imu.dictIMU;

import java.util.Scanner;
import java.util.ArrayList;

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
			System.out.println("1. Thêm từ mới");
			System.out.println("2. Hiện tất cả các từ");
			System.out.println("0. Thoát");
			System.out.println("=========================");
			System.out.print("Lựa chọn: ");
			c = scanner.next().charAt(0);
		  	switch (c)
		  	{
		  		case '1':
					dictionaryManager.insertFromCommandline();
		  			break;
		 		case '2':
					dictionaryCommandline.showAllWords(dictionaryManager.getDictionary().getWords());
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
			System.out.println("1. Thêm từ mới từ File");
			System.out.println("2. Hien tat ca cac tu");
            System.out.println("3. Tìm kiếm từ");
			System.out.println("0. Thoát");
			System.out.println("=========================");
			System.out.print("Lựa chọn: ");
			c = scanner.next().charAt(0);
		  	switch (c)
		  	{
		  		case '1':
					dictionaryManager.insertFromFile("resources/data/dictionary.txt");
		  			break;
		 		case '2':
					dictionaryCommandline.showAllWords(dictionaryManager.getDictionary().getWords());
					break;
                case '3':
                    System.out.print("Nhập từ cần tra: ");
                    String searchWord = scanner.next();
                    ArrayList<Word> resultList = dictionaryManager.dictionaryLookup(searchWord.toLowerCase());
                    dictionaryCommandline.showAllWords(resultList);
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