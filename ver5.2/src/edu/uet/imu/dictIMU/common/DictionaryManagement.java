package edu.uet.imu.dictIMU.common;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.Arrays;

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
    
    public void addWord(String wordTarget, String wordExplain)
    {
        Word word = new Word(wordTarget, wordExplain);
        dictionary.addWord(word);
    }
        
    public Word removeWord(String str)
    {
        Word word = lookup(str);
        if (word != null) 
        {
            dictionary.removeWord(word);
        }

        return word;

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
        addWord(eng, vi); 
       
	}

    public void removeFromCommandline()
    {
        Scanner scanner = new Scanner(System.in);    

        System.out.print("Enter the English word to remove: ");
        String str = scanner.nextLine();
        
        Word word = removeWord(str); 
        
        if (word == null)
        {
            System.out.println("Word not found!");
        }
    }

    public void insertFromFile(String path) 
    {
        // try 
        // {
            InputStream in = getClass().getResourceAsStream(path);
            Scanner scanner = new Scanner(in, "UTF-8");
            System.out.println("Get datas from " + path);
            while (scanner.hasNext())
            {
                String[] line = scanner.nextLine().split("\t", 2);
                if (line.length < 2)
                {
                    System.out.println("Error file's format!");
                    break;
                }
                
                addWord(line[0], line[1]);
            }
        // }
        // catch (FileNotFoundException e)
        // {
        //      System.out.println("File not found");
        // }
    }

    public void exportToFile(String path)
    {
        ArrayList<Word> dict = dictionary.getWords();
        PrintWriter printWriter = null;

        try 
        {
            Writer fileWriter = new FileWriter(path, false);
            printWriter = new PrintWriter(fileWriter);

            for (Word word: dict)
            {
                printWriter.write(word.getWordTarget() + "\t" + word.getWordExplain());
                printWriter.write(System.getProperty("line.separator"));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (printWriter != null)
                printWriter.close();
        }

    }


    /**
     * Search a word
     * @param searchWord word needed to search.
     * @return word
     */
    public Word lookup(String searchWord)
    {
        if (searchWord == null || searchWord == "")
            return null;
        
        ArrayList<Word> dict = dictionary.getWords();
        ArrayList<String> index = dictionary.getWordsIndex();
        
        String[] wordList = new String[index.size()];
        wordList = index.toArray(wordList);

        int i = Arrays.binarySearch(wordList, searchWord);

        if (i < 0)
            return null;
        return dict.get(i);
    }
}

