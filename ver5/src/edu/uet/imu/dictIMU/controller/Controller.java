package edu.uet.imu.dictIMU.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.collections.transformation.FilteredList;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.InputStream;
import java.io.IOException;

import edu.uet.imu.dictIMU.DictionaryManagement;
import edu.uet.imu.dictIMU.DictionarySearcher;
import edu.uet.imu.dictIMU.Translator;

public class Controller implements Initializable
{

	@FXML
	private TextField searchField;

	@FXML
	private ListView<String> resultList;

    @FXML
    private TextArea textArea;

    @FXML
    private TextArea logArea;

	private FilteredList<String> searchList;

	private DictionaryManagement dictionaryManager;
	
    public Controller() throws FileNotFoundException
	{
		dictionaryManager = new DictionaryManagement();
        dictionaryManager.insertFromFile("out/data/dictionary.txt");
        searchList = new FilteredList<>(dictionaryManager.getDictionary().getObservableWordsIndexList(), e -> true);
	}

	public void handleSearch(ActionEvent actionEvent)
	{
		String searchText =  searchField.getText();
		searchList = DictionarySearcher.searcherForApplication("", searchText.toLowerCase(), searchList);
        
        
        if (searchList.size() == 0)
        {
            // DEBUGListView javafx
            System.out.println(searchList.toString());
            ////////
            
            textArea.setText("Searching from Web....");
            Translator translator = new Translator();
            try 
            {
                textArea.setText(translator.callUrlAndParseResult("en", "vi", searchField.getText()));
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
        System.out.println(".............");
        Translator translator = new Translator();
        try 
        {
        System.out.print("Search From Web: " + translator.callUrlAndParseResult("en", "vi", searchField.getText()));
        }
        catch (Exception ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
		resultList.setItems(searchList);

		searchField.textProperty().addListener((observable, oldVal, newVal) ->  {
			searchList = DictionarySearcher.searcherForApplication(oldVal, newVal, searchList);
			resultList.setItems(searchList);
		});

        resultList.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null)
            {
                textArea.setText(dictionaryManager.dictionaryLookup(newVal).getWordExplain());
            }
        });

        logArea.setText("Press \"Enter\" to Search From Web");
	}

}
