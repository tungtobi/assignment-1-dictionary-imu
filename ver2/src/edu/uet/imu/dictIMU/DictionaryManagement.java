package edu.uet.imu.dictIMU;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

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

		System.out.print("- Nhập từ tiếng Anh: ");
		eng = scanner.nextLine();
		
		System.out.print("- Nhập giải thích sang tiếng Việt: ");
		vi = scanner.nextLine();

		Word word = new Word(eng, vi);
		dictionary.addWord(word);
	}

    public void insertFromFile(String path) 
    {
        try 
        {
            Scanner scanner = new Scanner(new File(path));

            System.out.println("Lấy dữ liệu từ file " + path);
            while (scanner.hasNext())
            {
                String[] line = scanner.nextLine().split("\t", 2);
                if (line.length < 2)
                {
                    System.out.println("Không thê lấy dữ liệu. Lỗi định dạng file!");
                    break;
                }

                Word word = new Word(line[0], line[1]);
                dictionary.addWord(word);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy file " + path);
        }
    }

    public ArrayList<Word> dictionaryLookup(String searchWord)
    {
        ArrayList<Word> dict = dictionary.getWords();
        ArrayList<Word> ret = new ArrayList<Word>();

        if (searchWord == null || searchWord == "")
            return dict;

        for (Word word : dict)
        {
            if (word.getWordTarget().toLowerCase().contains(searchWord))
                ret.add(word);
        }

        return ret;
    }
}

