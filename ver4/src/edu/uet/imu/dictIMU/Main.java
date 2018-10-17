package edu.uet.imu.dictIMU;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length != 0 && args[0].equals("commandline"))
        {
            DictionaryCommandLine dictionary = new DictionaryCommandLine();
            dictionary.dictionaryAdvenced();
        }
        else
        {
            DictionaryApplication application = new DictionaryApplication();
            application.runApplication(args);
    
        }
    }
}
