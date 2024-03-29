package edu.uet.imu.dictIMU.application.controller;

import edu.uet.imu.dictIMU.common.WordX;
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

import static edu.uet.imu.dictIMU.application.tools.AlertWindow.openAlertWindow;

public class EditWordController implements Initializable
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
    private WordX oldWord;

    public EditWordController(DictionaryManagement dictionaryManager, WordX oldWord)
    {
        this.dictionaryManager = dictionaryManager;
        this.oldWord = oldWord;
    }

    public void handleApplyButton(ActionEvent actionEvent) {
        String wordTarget = targetTextField.getText();
        String wordExplain = explainTextField.getText();
        String pronunciation = pronunciationField.getText();

        if (wordTarget == null || wordExplain == null || pronunciation == null ||
                wordTarget.equals("") || wordExplain.equals("") || pronunciation.equals("")) {
            openAlertWindow("Chỉnh sửa thất bại!");
        } else {
            dictionaryManager.removeWord(oldWord.getWordTarget());
            dictionaryManager.addWord(wordTarget, wordExplain, pronunciation);
            dictionaryManager.exportToFile("resources/data/out_dict.txt");

            handleCancelButton(actionEvent);
            openAlertWindow("Chỉnh sửa thành công!");
        }
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        targetTextField.setText(oldWord.getWordTarget());
        pronunciationField.setText(oldWord.getPronunciation());
        explainTextField.setText(oldWord.getWordExplain());
    }
}
