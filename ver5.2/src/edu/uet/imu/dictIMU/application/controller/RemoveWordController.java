package edu.uet.imu.dictIMU.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.application.tools.AlertWindow;

public class RemoveWordController implements Initializable
{
    @FXML
    private TextField targetTextField;

    @FXML
    private Button cancelButton;

    private DictionaryManagement dictionaryManager;
    private String word;

    public RemoveWordController(DictionaryManagement dictionaryManager, String word)
    {
        this.dictionaryManager = dictionaryManager;
        this.word = word;
    }

    public void handleApplyButton(ActionEvent actionEvent)
    {
        String wordTarget = targetTextField.getText();

        if (wordTarget == null || wordTarget.equals(""))
            return ;

        Word word = dictionaryManager.removeWord(wordTarget);
        
        if (word == null)
        {
            AlertWindow alertWindow = new AlertWindow("Word not found");
            try 
            {
                alertWindow.run();
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        
        handleCancelButton(actionEvent);
    }

    public void handleCancelButton(ActionEvent actionEvent)
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if (word != null && word != "")
            targetTextField.setText(word);

    }
}