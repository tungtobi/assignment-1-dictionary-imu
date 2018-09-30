package edu.uet.imu.dictIMU.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.application.tools.AlertWindow;

public class EditWordController implements Initializable
{
    @FXML
    private TextField targetTextField;
    
    @FXML
    private TextField explainTextField;

    @FXML
    private Button cancelButton;

    private DictionaryManagement dictionaryManager;
    private Word oldWord;

    public EditWordController(DictionaryManagement dictionaryManager, Word oldWord)
    {
        this.dictionaryManager = dictionaryManager;
        this.oldWord = oldWord;
    }

    public void handleApplyButton(ActionEvent actionEvent)
    {
        String wordTarget = targetTextField.getText();
        String wordExplain = explainTextField.getText();

        if (wordTarget == null || wordExplain == null ||wordTarget.equals("") || wordExplain.equals(""))
        {
            AlertWindow alertWindow = new AlertWindow("Cannot edit word");
            try
            {
                alertWindow.run();
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        else
        {
            dictionaryManager.removeWord(oldWord.getWordTarget());
            dictionaryManager.addWord(wordTarget, wordExplain);
            handleCancelButton(actionEvent);
        }
    }

    public void handleCancelButton(ActionEvent actionEvent)
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        targetTextField.setText(oldWord.getWordTarget());
        explainTextField.setText(oldWord.getWordExplain());
    }
}
