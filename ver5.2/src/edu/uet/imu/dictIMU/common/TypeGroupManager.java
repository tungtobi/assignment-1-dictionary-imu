package edu.uet.imu.dictIMU.common;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TypeGroupManager {

    public static String getType(JSONArray jsonArray) throws Exception
    {
        String type = "";
        if (!jsonArray.isNull(0))
        {
            type = jsonArray.get(0).toString();
        }
        return type;
    }

    public static Map<String, ArrayList<String>> getCloseWord(JSONArray jsonArray) throws Exception
    {
        Map<String, ArrayList<String>> closeWord = new HashMap<>();
        if (jsonArray.length() >= 3)
        {
            JSONArray jsonArray2 = (JSONArray) jsonArray.get(2);
            for (int i = 0; i < jsonArray2.length(); i++)
            {
                JSONArray jsonArray2i = (JSONArray) jsonArray2.get(i);
                String key = jsonArray2i.get(0).toString();
                JSONArray jsonArray2i1 = (JSONArray) jsonArray2i.get(1);
                ArrayList<String> values = new ArrayList<>();
                for (int j = 0; j < jsonArray2i1.length(); j++)
                    values.add(jsonArray2i1.get(j).toString());
                closeWord.put(key, values);
            }
        }

        return closeWord;
    }

    /*
    public static Map<String, Map<String, ArrayList<String>>> getTypeGroup(String typeGroup) throws Exception
    {
        Map<String, Map<String, ArrayList<String>>> ret = new HashMap<>();
        JSONArray jsonArray = new JSONArray(typeGroup);

        if (!jsonArray.isNull(0))
        {
            for (int i = 0; i < jsonArray.length(); i++)
            {
                String type = getType((JSONArray) jsonArray.get(i));
                Map<String, ArrayList<String>> closeWord = getCloseWord((JSONArray) jsonArray.get(i));
                ret.put(type, closeWord);
            }
        }

        return ret;
    }
    */

    public static Map<String, Map<String, ArrayList<String>>> getTypeGroup(JSONArray json) throws Exception
    {
        Map<String, Map<String, ArrayList<String>>> ret = new HashMap<>();
        if (!json.isNull(1))
        {
            JSONArray jsonArray = (JSONArray) json.get(1);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                String type = getType((JSONArray) jsonArray.get(i));
                Map<String, ArrayList<String>> closeWord = getCloseWord((JSONArray) jsonArray.get(i));
                ret.put(type, closeWord);
            }
        }

        return ret;
    }
}
