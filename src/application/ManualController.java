package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ManualController implements Initializable{

	@FXML 
	Pane pane;

	public static int board[][] = new int [9][9];



	static ObservableList<Node> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		list = FXCollections.observableArrayList();


		makeList();


		insertInField();




	}
	
	public void doneClicked() {
		
		EnterController.primaryStage.close();
		EnterController.board = board;
	}

	private void insertInField(){


		for (int i = 0; i < list.size(); i++) {

			TextField temp = ((TextField)list.get(i));

			double index=i;

			temp.textProperty().addListener((observable, oldValue, newValue) -> {

				int num=0;

				if(newValue.equals("")) {

					int row =((int)Math.ceil((index+1)/9.0))-1;
					int col = (int)(index-(row*9));

					board[row][col] = 0;

					return;

				}


				try {

					num = Integer.parseInt(temp.getText());


				} 
				catch (Exception e) {

					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("You can only use Numbers from 1 to 9 !!");
					a.show();

					return;


				}


				int row =((int)Math.ceil((index+1)/9.0))-1;
				int col = (int)(index-(row*9));

				board[row][col] = num;



				if(!checkSol(temp,row,col)) {

					board[row][col]=0;

					return;
				}





			});

		}
	}



	public boolean checkSol(TextField temp,int row,int col) {



		String result = Sudoku.isSafe(row, col, Integer.parseInt(temp.getText()), board);

		if(result == null)
			return true;



		if(result.equalsIgnoreCase("Number")) {

			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("You can only use Numbers from 1 to 9 !!");
			a.show();

			temp.setText("");

			return false;
		}
		else if(result.equalsIgnoreCase("row")) {

			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(temp.getText() + "  Are Used in the Row");
			a.show();

			temp.setText("");

			return false;

		}

		else if(result.equalsIgnoreCase("col")){

			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(temp.getText() + "  Are Used in the Coloum");
			a.show();

			temp.setText("");

			return false;


		}
		else {

			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(temp.getText() + "  Are Used in the Box");
			a.show();

			temp.setText("");

			return false;

		}

	}



	public void makeList() {

		for (int k=0 ; k < 9 ;k+=3) {

			for (int i = k; i < k+3; i++) {

				Pane temp = (Pane)pane.getChildren().get(i);

				for (int j = 0; j < 3; j++) {

					list.add(temp.getChildren().get(j));


				}

			}



			for (int i = k; i < k+3; i++) {

				Pane temp = (Pane)pane.getChildren().get(i);

				for (int j = 3; j < 6; j++) {

					list.add(temp.getChildren().get(j));


				}

			}

			for (int i = k; i < k+3; i++) {

				Pane temp = (Pane)pane.getChildren().get(i);

				for (int j = 6; j < 9; j++) {

					list.add(temp.getChildren().get(j));


				}

			}

		}

	}



}
