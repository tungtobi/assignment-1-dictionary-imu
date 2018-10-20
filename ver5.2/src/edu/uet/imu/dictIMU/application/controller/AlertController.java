package edu.uet.imu.dictIMU.application.controller;

import edu.uet.imu.dictIMU.application.tools.AlertWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import edu.uet.imu.dictIMU.common.DictionaryManagement;

public class AlertController implements Initializable
{
    @FXML
    private Text messageText;

    @FXML
    private Button cancelButton;

    private String message;

    public AlertController(String message)
    {
        this.message = message;
    }

    public void handleButton(ActionEvent actionEvent)
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        messageText.setText(message);
    }
}
