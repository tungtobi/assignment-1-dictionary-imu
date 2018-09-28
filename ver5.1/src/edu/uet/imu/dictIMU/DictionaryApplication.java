package edu.uet.imu.dictIMU;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DictionaryApplication extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception
	{

		Font.loadFont(getClass().getResource("/fonts/EBGaramond-Regular.ttf").toExternalForm(), 14);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
		primaryStage.setTitle("Dict");
		primaryStage.setScene(new Scene(root, 920, 720));
		primaryStage.show();
	}


	public static void runApplication(String[] args)
	{
		launch(args);
	}
}
