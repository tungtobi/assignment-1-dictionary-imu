package edu.uet.imu.dictIMU;

import edu.uet.imu.dictIMU.application.DictionaryApplication;
import edu.uet.imu.dictIMU.commandLine.DictionaryCommandLine;

public class Main
{
    public static void main(String[] args)
    {
        DictionaryCommandLine dictionary = new DictionaryCommandLine();
        dictionary.dictionaryAdvenced();
    }
}
