package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnterController implements Initializable{

	@FXML
	RadioButton easy,meduim,hard;

	ToggleGroup tg = new ToggleGroup();

	static int [][] board ;

	static Stage primaryStage;

	boolean isManual=false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		isManual=false;

		easy.setToggleGroup(tg);
		meduim.setToggleGroup(tg);
		hard.setToggleGroup(tg);

		board = new int [9][9];

	}

	public void manualGame() throws IOException {
		isManual = true;

		primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("manual.fxml"));

		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(scene);

		primaryStage.initModality(Modality.APPLICATION_MODAL);

		primaryStage.show();



	}

	public void startGame() throws IOException {

		if(isManual) {

			isManual = false;

			Controller.board = board;



			Parent root = FXMLLoader.load(getClass().getResource("SudokuS.fxml"));


			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Main.getPrimaryStage().setScene(scene);


		}

		else if(meduim.isSelected()) {

			generateGame("Meduim");
		}
		else if(hard.isSelected()) {

			generateGame("Hard");
		}
		else {
			generateGame("Easy");	
		}
	}


	public void generateGame(String level) throws IOException {

		Sudoku.generate(level, board);
		Sudoku.board =board;


		Controller.board = Sudoku.board;



		Parent root = FXMLLoader.load(getClass().getResource("SudokuS.fxml"));


		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Main.getPrimaryStage().setScene(scene);



	}
}
