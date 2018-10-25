package edu.uet.imu.dictIMU.application.controller;

import edu.uet.imu.dictIMU.application.tools.Helper;
import edu.uet.imu.dictIMU.common.WordX;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.collections.transformation.FilteredList;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.InputStream;
import java.io.IOException;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javazoom.jl.decoder.JavaLayerException;

import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.tools.DictionarySearcher;
import edu.uet.imu.dictIMU.tools.Translator;
import edu.uet.imu.dictIMU.tools.TextToSpeechGoogle;

// check Edit Word Application
import edu.uet.imu.dictIMU.application.tools.AddWordApplication;
import edu.uet.imu.dictIMU.application.tools.RemoveWordApplication;
import edu.uet.imu.dictIMU.application.tools.EditWordApplication;
import org.json.JSONArray;
//////////////////////////


public class Controller implements Initializable
{
    @FXML
    private VBox textArea;

    @FXML
    private VBox imgDashboard;

    @FXML
    private Button btnRemove, btnEdit;

    @FXML
	private TextField searchField;

	@FXML
	private ListView<String> resultList;

    @FXML
    private TextArea textExplain;

    @FXML
    private Text textTarget;

    @FXML
    private BorderPane mainPane;

	private FilteredList<String> searchList;

	private DictionaryManagement dictionaryManager;
	
    public Controller() throws FileNotFoundException
	{
		dictionaryManager = new DictionaryManagement();
        //dictionaryManager.insertFromFile("/data/dictionary.txt");
        dictionaryManager.readFromFile("out_dict.txt");
        refeshSearchList();

	}

    public void refeshSearchList() {
        searchList = new FilteredList<>(dictionaryManager.getDictionary().getObservableWordsIndexList(), e -> true);
    }

	public void handleSearch(ActionEvent actionEvent)
	{
		String searchText =  searchField.getText().trim();

        if (searchText.equals(""))
            return;

        // DEBUGListView javafx
        System.out.println(searchList.toString());
        ////////
        
        searchFromWeb(searchText);

		resultList.setItems(searchList);
	}

	public void searchFromWeb(String text) {
        textExplain.setText("Tìm kiếm online...");
        Translator translator = new Translator();
        try
        {
            textTarget.setText(text);
            translator.callUrl("en", "vi", text);
            if (translator.isSuccess())
            {
                WordX result = translator.fullTranslate();

                if (!dictionaryManager.isExist(result.getWordTarget())) {
                    dictionaryManager.addWord(result);
                    dictionaryManager.appendToFile(result);

                    refeshSearchList();
                }

                textExplain.setText(Helper.WordXToTextFX(result));
            }
            else
            {
                textExplain.setText("Không tìm thấy " + text + "!");
            }
            showContents();
        }
        catch (Exception ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	public void handleSearchField(ActionEvent actionEvent)
	{
		Thread searchThread = new Thread(() -> {
		    handleSearch(actionEvent);
        });
        searchThread.start();

//        Platform.runLater(() -> {
//                handleSearch(actionEvent);
//            }
//        );

        // DEBUG
        System.out.println("handleSearchField: OnAction");
	}

    public void handleTextToSpeed(ActionEvent actionEvent)
    {
        System.out.println(".......Playing");
        //String text = resultList.getSelectionModel().getSelectedItem();
        String text = textTarget.getText();

        Button speaker = (Button) actionEvent.getSource();
        String targetLang = speaker.getId();

        Thread playAudioThread = new Thread(() -> {
            playTextToSpeechAudio(text, targetLang);
        });
        playAudioThread.start();

//        Platform.runLater(() -> {
//                playTextToSpeechAudio(text, targetLang);
//            }
//        );
    }

    public void playTextToSpeechAudio(String text, String targetLang) {
        TextToSpeechGoogle audio = new TextToSpeechGoogle();
        InputStream sound = null;

        if (text == null)
            text = searchField.getText();

        try {
            sound = audio.getAudio(text, targetLang);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (sound != null)
                audio.play(sound);
        } catch (JavaLayerException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
	    hideContents();

		resultList.setItems(searchList);

		searchField.textProperty().addListener((observable, oldVal, newVal) ->  {
			searchList = DictionarySearcher.searcherForApplication(oldVal, newVal, searchList);
			resultList.setItems(searchList);
		});

        setResultListListener();
	}
	

	public void setResultListListener() {
        resultList.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null)
            {
                WordX word = dictionaryManager.lookup(newVal);

                textTarget.setText(word.getWordTarget());
                //textExplain.setText(word.getPronunciation() + "\n" + word.getWordExplain());
                textExplain.setText(Helper.WordXToTextFX(word));
                showContents();
            }
        });
    }

    // Check Edit Word Application
    public void handleAddWord(ActionEvent actionEvent)
    {
        mainPane.setEffect(new BoxBlur(3, 3, 5));

        AddWordApplication app = new AddWordApplication(dictionaryManager);
        try {
            app.run();

            System.out.println("Done-------------------");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        mainPane.setEffect(null);

        refeshSearchList();
		resultList.setItems(searchList);
    }
    
    public void handleRemoveWord(ActionEvent actionEvent)
    {
        String word = resultList.getSelectionModel().getSelectedItem();

        if (word != null) {
            mainPane.setEffect(new BoxBlur(3, 3, 5));
            openRemoveWordApp(word);
        }

        mainPane.setEffect(null);
    }

    public void openRemoveWordApp(String word) {
        RemoveWordApplication app = new RemoveWordApplication(dictionaryManager, word);
        try {
            app.run();
            System.out.println("Done-------------------");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        int oldSize = searchList.size();

        refeshSearchList();
        resultList.setItems(searchList);

        int newSize = searchList.size();

        if (newSize != oldSize)
            hideContents();
    }

    public void hideContents() {
        textArea.setVisible(false);
        imgDashboard.setVisible(true);
        btnEdit.setVisible(false);
        btnRemove.setVisible(false);
    }

    public void showContents() {
        textArea.setVisible(true);
        imgDashboard.setVisible(false);
        btnEdit.setVisible(true);
        btnRemove.setVisible(true);
    }
    
    public void handleEditWord(ActionEvent actionEvent) {
        WordX word = dictionaryManager.lookup(resultList.getSelectionModel().getSelectedItem());
        if (word != null) {
            mainPane.setEffect(new BoxBlur(3, 3, 5));
            openEditWordApp(word);
            mainPane.setEffect(null);
        }
    }

    public void openEditWordApp(WordX word) {
        EditWordApplication app = new EditWordApplication(dictionaryManager, word);
        try {
            app.run();
            System.out.println("Done-------------------");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        refeshSearchList();
        resultList.setItems(searchList);

        setResultListListener();
    }
}
