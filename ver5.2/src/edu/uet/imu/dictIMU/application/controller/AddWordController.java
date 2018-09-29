package edu.uet.imu.dictIMU.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import edu.uet.imu.dictIMU.common.DictionaryManagement;

public class AddWordController
{
    @FXML
    private TextField targetTextField;
    
    @FXML
    private TextField explainTextField;

    @FXML
    private Button cancelButton;

    private DictionaryManagement dictionaryManager;

    public AddWordController(DictionaryManagement dictionaryManager)
    {
        this.dictionaryManager = dictionaryManager;
    }

    public void handleApplyButton(ActionEvent actionEvent)
    {
        String wordTarget = targetTextField.getText();
        String wordExplain = explainTextField.getText();

        if (wordTarget == null || wordExplain == null)
            return ;
        if (wordTarget.equals("") || wordExplain.equals(""))
            return ;

        dictionaryManager.addWord(wordTarget, wordExplain);
        handleCancelButton(actionEvent);
    }

    public void handleCancelButton(ActionEvent actionEvent)
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
