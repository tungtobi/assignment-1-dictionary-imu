package edu.uet.imu.dictIMU.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.application.tools.AlertWindow;

import static edu.uet.imu.dictIMU.application.tools.AlertWindow.openAlertWindow;

public class RemoveWordController implements Initializable {
    @FXML
    private Label textTitle;

    @FXML
    private Button cancelButton;

    private DictionaryManagement dictionaryManager;
    private String word;
    private Controller controller;

    public RemoveWordController(DictionaryManagement dictionaryManager, String word) {
        this.dictionaryManager = dictionaryManager;
        this.word = word;
    }

    public void handleApplyButton(ActionEvent actionEvent) {
        String wordTarget = word;

        if (wordTarget == null || wordTarget.equals(""))
            return ;

        dictionaryManager.removeWord(wordTarget);
        dictionaryManager.exportToFile("resources/data/out_dict.txt");

        openAlertWindow("Xóa thành công!");

        handleCancelButton(actionEvent);
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textTitle.setText("Xóa từ '" + word + "'?");
    }
}
