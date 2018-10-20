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
import edu.uet.imu.dictIMU.application.tools.AlertWindow;

public class AddWordController
{
    @FXML
    private TextField targetTextField;
    
    @FXML
    private TextField explainTextField;

    @FXML
    private TextField pronunciationField;

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
        String pronunciation = pronunciationField.getText();

        if (wordTarget == null || wordExplain == null || pronunciation == null ||
                wordTarget.equals("") || wordExplain.equals("") || pronunciation.equals("")) {
            AlertWindow alertWindow = new AlertWindow("Không thể thêm từ");
            try {
                alertWindow.run();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            dictionaryManager.addWord(wordTarget, wordExplain, pronunciation);
            dictionaryManager.exportToFile("resources/data/out_dict.txt");
            handleCancelButton(actionEvent);
        }
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        AlertWindow alertWindow = new AlertWindow("Thêm từ thành công");
        try {
            alertWindow.run();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
