package edu.uet.imu.dictIMU.tools;

// Source: http://archana-testing.blogspot.com/2016/02/calling-google-translation-api-in-java.html

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.common.WordX;
import org.json.JSONArray;

public class Translator
{

    private JSONArray json;

    public Translator()
    {
        this.json = null;
    }

    public void callUrl(String langFrom, String langTo, String word) throws Exception
    {
        if (langFrom.equals(""))
        {
            langFrom = "auto";
        }
        // String url = "https://translate.googleapis.com/translate_a/single?"+
        //             "client=gtx&"+
        //             "sl=" + langFrom + 
        //             "&tl=" + langTo + 
        //             "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");    

        String url = "https://translate.googleapis.com/translate_a/single?" +
                "client=gtx&" +
                "sl=" + langFrom +
                "&tl=" + langTo +
                "&hl=en" +
                "&dt=at" +
                "&dt=bd" +
                "&dt=ex" +
                "&dt=ld" +
                "&dt=md" +
                "&dt=qca" +
                "&dt=rw" +
                "&dt=rm" +             // [[[null,null,null,"bo͝ok"]],null,"en",null,null,null,1,null,[["en"],null,[1],["en"]]]
                "&dt=ss" +
                "&dt=t" +            // [[["sách","book",null,null,1]],null,"en"]

                "&ie=UTF-8&oe=UTF-8" +
                "&q=" + URLEncoder.encode(word, "UTF-8");
        // rm t ss


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();

        json = new JSONArray(response.toString());

        System.out.println(json);


    }

    public String simpleTranslate() throws Exception
    {
        JSONArray jsonArray2 = (JSONArray) json.get(0);
        JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);

        return jsonArray3.get(0).toString();
    }

    public String getPron() throws Exception
    {
        JSONArray jsonArray0 = (JSONArray) json.get(0);

        String pron = "";
        if (jsonArray0.length() > 1)
        {
            JSONArray jsonArray01 = (JSONArray) jsonArray0.get(1);
            pron = jsonArray01.get(jsonArray01.length() - 1).toString();
        }
        return pron;
    }

    public String getType(JSONArray jsonArray) throws Exception
    {
        String type = "";
        if (!jsonArray.isNull(0))
        {
            type = jsonArray.get(0).toString();
        }
        return type;
    }

    public Map<String, ArrayList<String>> getCloseWord(JSONArray jsonArray) throws Exception
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

    public Map<String, Map<String, ArrayList<String>>> getTypeGroup() throws Exception
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

    public String getTarget() throws Exception
    {
        JSONArray jsonArray0 = (JSONArray) json.get(0);
        JSONArray jsonArray00 = (JSONArray) jsonArray0.get(0);
        return jsonArray00.get(1).toString();
    }

    public String getExplain() throws Exception
    {
        JSONArray jsonArray0 = (JSONArray) json.get(0);
        JSONArray jsonArray00 = (JSONArray) jsonArray0.get(0);
        return jsonArray00.get(0).toString();
    }

    public boolean isSuccess() throws Exception
    {
        if (json == null)
            return false;

        String target = getTarget().toLowerCase();
        String explain = getExplain().toLowerCase();

        return !(target.equals(explain) && ((JSONArray) json.get(0)).length() <= 1 && json.isNull(1));

    }

    public WordX fullTranslate() throws Exception
    {

        String target = getTarget();
        String explain = getExplain();

        String pron = getPron();

        Map<String, Map<String, ArrayList<String>>> closeWordGroup = getTypeGroup();

        return new WordX(target, explain, pron, closeWordGroup);
    }
}
