package edu.uet.imu.dictIMU.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

import javafx.scene.layout.VBox;
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
    private Text textExplain;

    @FXML
    private Text textTarget;

	private FilteredList<String> searchList;

	private DictionaryManagement dictionaryManager;
	
    public Controller() throws FileNotFoundException
	{
		dictionaryManager = new DictionaryManagement();
        dictionaryManager.insertFromFile("resources/data/dictionary.txt");
        searchList = new FilteredList<>(dictionaryManager.getDictionary().getObservableWordsIndexList(), e -> true);
	}



	public void handleSearch(ActionEvent actionEvent)
	{
		String searchText =  searchField.getText();
		// searchList = DictionarySearcher.searcherForApplication("", searchText.toLowerCase(), searchList);
        
        
        //if (searchList.size() == 0)
        {
            // DEBUGListView javafx
            System.out.println(searchList.toString());
            ////////
            
            textExplain.setText("Searching from Web....");
            Translator translator = new Translator();
            try 
            {
                textTarget.setText(searchText);
                textExplain.setText(translator.callUrlAndParseResult("en", "vi", searchField.getText()));
                showContents();
            }
            catch (Exception ex)
            {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
		resultList.setItems(searchList);
	}

	public void handleSearchField(ActionEvent actionEvent)
	{
		handleSearch(actionEvent);
        
        // DEBUG
        System.out.println("handleSearchField: OnAction");
	}

    public void handleTextToSpeed(ActionEvent actionEvent)
    {
        System.out.println(".......Playing");
        TextToSpeechGoogle audio = new TextToSpeechGoogle();
        InputStream sound = null;

        String text = resultList.getSelectionModel().getSelectedItem();
        if (text == null)
            text = searchField.getText();

        try {
            sound = audio.getAudio(text, "en");
        } 
        catch (IOException ex)
        {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (sound != null)
                audio.play(sound);
        } 
        catch (JavaLayerException ex)
        {
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

        resultList.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null)
            {
                textTarget.setText(newVal);
                textExplain.setText(dictionaryManager.lookup(newVal).getWordExplain());
                showContents();
            }
        });
	}
	

    // Check Edit Word Application
    public void handleAddWord(ActionEvent actionEvent)
    {
        AddWordApplication app = new AddWordApplication(dictionaryManager);
        try
        {
            app.run();

            System.out.println("Done-------------------");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        searchList = new FilteredList<>(dictionaryManager.getDictionary().getObservableWordsIndexList(), e -> true);
		resultList.setItems(searchList);
    }
    
    public void handleRemoveWord(ActionEvent actionEvent)
    {
        String word = resultList.getSelectionModel().getSelectedItem();
        if (word != null) {
            openRemoveWordApp(word);
        }
    }

    public void openRemoveWordApp(String word) {
        RemoveWordApplication app = new RemoveWordApplication(dictionaryManager, word);
        try
        {
            app.run();

            System.out.println("Done-------------------");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());

        }

        int oldSize = searchList.size();

        searchList = new FilteredList<>(dictionaryManager.getDictionary().getObservableWordsIndexList(), e -> true);
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
        Word word = dictionaryManager.lookup(resultList.getSelectionModel().getSelectedItem());
        if (word != null) {
            openEditWordApp(word);
        }
    }

    public void openEditWordApp(Word word) {
        EditWordApplication app = new EditWordApplication(dictionaryManager, word);
        try
        {
            app.run();

            System.out.println("Done-------------------");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        searchList = new FilteredList<>(dictionaryManager.getDictionary().getObservableWordsIndexList(), e -> true);
        resultList.setItems(searchList);
    }
    //////////////////////////////

}
