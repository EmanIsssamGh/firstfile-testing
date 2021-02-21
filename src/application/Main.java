package application;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main  extends Application{

	private static Stage primaryStage;


	@Override
	public void start(Stage primaryStage) throws Exception{


		setPrimaryStage(primaryStage); 
		
		Parent root = FXMLLoader.load(getClass().getResource("Enter.fxml"));

		

		Scene scene = new Scene(root);

		




		primaryStage.setScene(scene);
		primaryStage.setTitle("Sudoku");
		



		primaryStage.show();

	}


	static public Stage getPrimaryStage() {
		return Main.primaryStage;
	}

	private void setPrimaryStage(Stage stage) {
		Main.primaryStage = stage;
	}




	public static void main(String[] args) throws IOException {

		launch(args);



	}


}
