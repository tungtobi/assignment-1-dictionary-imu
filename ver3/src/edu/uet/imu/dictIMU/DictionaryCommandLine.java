package edu.uet.imu.dictIMU;

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
			System.out.println("1. Thêm từ mới từ File");
			System.out.println("2. Hiện tất cả các từ");
            System.out.println("3. Tìm kiếm từ");
            System.out.println("4. Xóa từ");
            System.out.println("5. Xuất từ điển ra file");
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
					showAllWords(dictionaryManager.getDictionary().getWords());
					break;
                case '3':
                    System.out.print("Nhập từ cần tra: ");
                    String searchWord = scanner.next();
                    ArrayList<Word> resultWords = dictionarySearcher(searchWord.toLowerCase());
                    if (resultWords == null)
                        System.out.println("Không tìm thấy từ cần tra");
                    else 
                        showAllWords(resultWords);
                    break;
                case '4':
                    dictionaryManager.removeFromCommandline();
                    break;
                case '5':
                    dictionaryManager.dictionaryExportToFile("resources/data/out.txt");
			}
			System.out.println("=========================");
		}
		while (c != '0');

    }

    public ArrayList<Word> dictionarySearcher(String searchWord)
    {
        ArrayList<Word> dict = dictionary.getWords();
        if (searchWord == null || searchWord == "")
            return dict;
        
        ArrayList<String> index = dictionary.getWordsIndex();
        ArrayList<Word> res = new ArrayList<Word>();

        for (int i = 0; i < index.size(); i++)
        {
            if (index.get(i).toLowerCase().contains(searchWord))
                res.add(dict.get(i));
        }
        
        return res;
    }

    

	public static void main(String[] args)
	{
		DictionaryCommandLine dictionary = new DictionaryCommandLine();
		dictionary.dictionaryAdvenced();
	}
}
