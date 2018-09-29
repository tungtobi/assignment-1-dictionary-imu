package edu.uet.imu.dictIMU.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.application.tools.AlertWindow;

public class RemoveWordController
{
    @FXML
    private TextField targetTextField;

    @FXML
    private Button cancelButton;

    private DictionaryManagement dictionaryManager;

    public RemoveWordController(DictionaryManagement dictionaryManager)
    {
        this.dictionaryManager = dictionaryManager;
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
}
