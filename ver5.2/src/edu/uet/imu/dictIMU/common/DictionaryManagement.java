package edu.uet.imu.dictIMU.common;

import java.io.FileInputStream;
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
    
    public void addWord(String wordTarget, String wordExplain, String pronunciation) {
        WordX word = new WordX(wordTarget, wordExplain, pronunciation, null);
        dictionary.addWord(word);
    }

    public void addWord(WordX word) {
	    dictionary.addWord(word);
    }

    public WordX removeWord(String str) {
        WordX word = lookup(str);
        if (word != null) 
        {
            dictionary.removeWord(word);
        }

        return word;

    }

    public void readFromFile(String path) {
        try {
            InputStream in = new FileInputStream(path);
            Scanner scanner = new Scanner(in, "UTF-8");
            System.out.println("Get datas from " + path);
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split("\t", 3);
                if (line.length < 2) {
                    System.out.println("Error file's format!");
                    break;
                }

                addWord(line[0], line[2], line[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void exportToFile(String path)
    {
        ArrayList<WordX> dict = dictionary.getWords();
        PrintWriter printWriter = null;

        try {
            Writer fileWriter = new FileWriter(path, false);
            printWriter = new PrintWriter(fileWriter);

            for (WordX word: dict)
            {
                printWriter.write(word.getWordTarget() + "\t" + word.getPronunciation() + "\t" + word.getWordExplain());
                printWriter.write(System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null)
                printWriter.close();
        }
    }

    public void appendToFile(WordX word) {
        PrintWriter printWriter = null;
        String path = "resources/data/out_dict.txt";

        try {
            Writer fileWriter = new FileWriter(path, true);
            printWriter = new PrintWriter(fileWriter);

            printWriter.write(word.getWordTarget() +
                    "\t" + word.getPronunciation() + "\t" + word.getWordExplain().toLowerCase() );
            printWriter.write(System.getProperty("line.separator"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null)
                printWriter.close();
        }
    }

    /**
     * Search a word
     * @param searchWord word needed to search.
     * @return word
     */
    public WordX lookup(String searchWord) {
        if (searchWord == null || searchWord == "")
            return null;
        
        ArrayList<WordX> dict = dictionary.getWords();
        ArrayList<String> index = dictionary.getWordsIndex();
        
        String[] wordList = new String[index.size()];
        wordList = index.toArray(wordList);

        int i = Arrays.binarySearch(wordList, searchWord);

        if (i < 0)
            return null;
        return dict.get(i);
    }

    public boolean isExist(String wordTarget) {
        return lookup(wordTarget) != null;
    }
}