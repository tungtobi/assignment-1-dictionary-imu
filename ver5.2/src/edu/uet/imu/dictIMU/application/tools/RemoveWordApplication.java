package edu.uet.imu.dictIMU.application.tools;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;


import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.application.controller.RemoveWordController;

public class RemoveWordApplication  
{
    private DictionaryManagement dictionaryManager;

    public RemoveWordApplication()
    {
        dictionaryManager = null;
    }

    public RemoveWordApplication(DictionaryManagement dictionaryManager)
    {
        this.dictionaryManager = dictionaryManager;
    }

    public void setDictionaryManagement(DictionaryManagement dictionaryManager)
    {
        this.dictionaryManager = dictionaryManager;
    }


    public void run() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RemoveWordApplication.fxml"));

        RemoveWordController controller = new RemoveWordController(dictionaryManager);

        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
		stage.setTitle("Remove word");
		stage.setScene(new Scene(root, 400, 400));
        stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
    }

}
