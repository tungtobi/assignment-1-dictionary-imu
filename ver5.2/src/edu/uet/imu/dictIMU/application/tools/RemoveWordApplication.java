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
    private String word;

    public RemoveWordApplication()
    {
        dictionaryManager = null;
        word = null;
    }

    public RemoveWordApplication(DictionaryManagement dictionaryManager)
    {
        this.dictionaryManager = dictionaryManager;
        this.word = null;
    }


    public RemoveWordApplication(DictionaryManagement dictionaryManager, String word)
    {
        this.dictionaryManager = dictionaryManager;
        this.word = word;
    }

    public void setDictionaryManagement(DictionaryManagement dictionaryManager)
    {
        this.dictionaryManager = dictionaryManager;
    }

    public void setWord(String word)
    {
        this.word = word;
    }


    public void run() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RemoveWordApplication.fxml"));

        RemoveWordController controller = new RemoveWordController(dictionaryManager, word);

        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
		stage.setTitle("Xóa từ");
		stage.setScene(new Scene(root, 300, 160));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
		stage.showAndWait();
    }

}
