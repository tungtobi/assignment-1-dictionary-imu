package edu.uet.imu.dictIMU;

import java.util.Scanner;
import java.util.ArrayList;
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
        dictionary.sortList();
        
    }

    public void removeFromCommandline()
    {
        Scanner scanner = new Scanner(System.in);    

        System.out.print("Enter the English word to remove: ");
        String str = scanner.nextLine();

        Word word = dictionaryLookup(str);
        if (word == null)
        {
            System.out.println("Word not found!");
            return;
        }
        else
        {
            dictionary.removeWord(word);
        }
    }

    public void insertFromFile(String path) 
    {
        try 
        {
            Scanner scanner = new Scanner(new File(path));

            System.out.println("Get datas from " + path);
            while (scanner.hasNext())
            {
                String[] line = scanner.nextLine().split("\t", 2);
                if (line.length < 2)
                {
                    System.out.println("Error file's format!");
                    break;
                }

                Word word = new Word(line[0], line[1]);
                dictionary.addWord(word);
            }
            dictionary.sortList();
        }
        catch (FileNotFoundException e)
        {
             System.out.println("File not found");
        }
    }

    public void dictionaryExportToFile(String path)
    {
        ArrayList<Word> dict = dictionary.getWords();
        PrintWriter printWriter = null;

        try 
        {
            Writer fileWriter = new FileWriter(path, true);
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

    public Word dictionaryLookup(String searchWord)
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

