package edu.uet.imu.dictIMU.application.tools;

import edu.uet.imu.dictIMU.common.WordX;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;


import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.application.controller.EditWordController;
import javafx.stage.StageStyle;

public class EditWordApplication  
{
    private DictionaryManagement dictionaryManager;
    private WordX oldWord;

    public EditWordApplication()
    {
        dictionaryManager = null;
        oldWord = null;
    }

    public EditWordApplication(DictionaryManagement dictionaryManager, WordX word)
    {
        this.dictionaryManager = dictionaryManager;
        this.oldWord = word;
    }

    public void setDictionaryManagement(DictionaryManagement dictionaryManager)
    {
        this.dictionaryManager = dictionaryManager;
    }

    public void setWord(WordX word)
    {
        this.oldWord = word;
    }

    public void run() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditWordApplication.fxml"));

        EditWordController controller = new EditWordController(dictionaryManager, oldWord);

        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Edit word");
		stage.setScene(new Scene(root, 300, 300));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
		stage.showAndWait();
    }

}
