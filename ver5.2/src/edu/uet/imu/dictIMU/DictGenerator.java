package edu.uet.imu.dictIMU;

import edu.uet.imu.dictIMU.application.controller.Controller;
import edu.uet.imu.dictIMU.application.tools.Helper;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.common.WordX;
import edu.uet.imu.dictIMU.tools.Translator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DictGenerator {
    public void insertFromFile(int step) {
        String path = "resources/data/dict.txt";
        try {
            InputStream in = new FileInputStream(path);
        Scanner scanner = new Scanner(in, "UTF-8");
        System.out.println("Get datas from " + path);
        int i = 0;
        while (scanner.hasNext() && i < step)
        {
            String[] line = scanner.nextLine().split("\t", 2);
            if (line.length < 1)
            {
                System.out.println("Error file's format!");
                break;
            }
            searchFromWeb(line[0]);
            i++;
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void searchFromWeb(String text) {
        Translator translator = new Translator();
        try
        {
            translator.callUrl("en", "vi", text);
            if (translator.isSuccess()) {
                WordX result = translator.fullTranslate();
                exportToFile(result);
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportToFile(WordX word)
    {
        PrintWriter printWriter = null;
        String path = "resources/data/out_dict.txt";

        try
        {
            Writer fileWriter = new FileWriter(path, true);
            printWriter = new PrintWriter(fileWriter);

            printWriter.write(word.getWordTarget() + "\t/" + word.getPronunciation() + "/\t" + word.getWordExplain().toLowerCase());
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
}
