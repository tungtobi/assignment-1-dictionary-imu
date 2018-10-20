package edu.uet.imu.dictIMU.application.tools;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;

import edu.uet.imu.dictIMU.application.controller.AlertController;

public class AlertWindow  
{
    private String message;

    public AlertWindow()
    {
        message = "";
    }

    public AlertWindow(String message)
    {
        this.message = message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public static void openAlertWindow(String dialogue) {
        AlertWindow alertWindow = new AlertWindow(dialogue);
        try {
            alertWindow.run();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void run() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlertWindow.fxml"));

        AlertController controller = new AlertController(message);

        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
		stage.setTitle("Alert");
		stage.setScene(new Scene(root, 300, 150));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
		stage.showAndWait();
    }

}
