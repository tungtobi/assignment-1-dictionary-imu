package edu.uet.imu.dictIMU.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Platform;

public class DictionaryApplication extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception
	{

		Font.loadFont(getClass().getResource("/fonts/EBGaramond-Regular.ttf").toExternalForm(), 14);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
		primaryStage.setTitle("Từ điển IMU");
		primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
		primaryStage.show();

        primaryStage.setOnCloseRequest(e -> Platform.exit());
	}


	public void runApplication(String[] args)
	{
		launch(args);
	}
}
