// https://stackoverflow.com/questions/35002003/how-to-use-google-translate-tts-with-the-new-v2-api
// -> https://translate.google.com/translate_tts?ie=UTF-8&tl=en&client=tw-ob&q=english

package edu.uet.imu.dictIMU.tools;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.URL;
import java.net.HttpURLConnection;
import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;

public class TextToSpeechGoogle
{
    public InputStream getAudio(String word, String targetLang) throws IOException
    {
        if (word == null)
            return null;
        String url = "https://translate.google.com/translate_tts?ie=UTF-8&tl=" + targetLang + "&client=tw-ob&q=" + URLEncoder.encode(word, "UTF-8");
        URL obj = new URL(url);
        HttpURLConnection urlConn = (HttpURLConnection) obj.openConnection();
        urlConn.setRequestProperty("User-Agent", "Mozilla/5.0");

        InputStream audioSrc = urlConn.getInputStream();
            
        // DEBUG
        System.out.println(audioSrc.toString());
        // ====

        return new BufferedInputStream(audioSrc); 
    }

    public void play(InputStream sound) throws JavaLayerException
    {
        (new Player(sound)).play();
    }

}

