package edu.uet.imu.dictIMU.application.tools;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;


import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.application.controller.AddWordController;

public class AddWordApplication  
{
    private DictionaryManagement dictionaryManager;

    public AddWordApplication()
    {
        dictionaryManager = null;
    }

    public AddWordApplication(DictionaryManagement dictionaryManager)
    {
        this.dictionaryManager = dictionaryManager;
    }

    public void setDictionaryManagement(DictionaryManagement dictionaryManager)
    {
        this.dictionaryManager = dictionaryManager;
    }


    public void run() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddWordApplication.fxml"));

        AddWordController controller = new AddWordController(dictionaryManager);

        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
		stage.setTitle("Add new word");
		stage.setScene(new Scene(root, 400, 400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
		stage.showAndWait();
    }

}
