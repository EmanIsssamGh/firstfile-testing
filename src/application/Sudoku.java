package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.TextField;


public class Sudoku{

	static int board[][] = new int [9][9];




	public static boolean isFull() {

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board.length; j++) {

				if(board[i][j] == 0)
					return false;

			}
		}
		return true;

	}

	public static ArrayList<Integer> possibleEntries(int i,int j){

		ArrayList<Integer> list = new ArrayList<>();

		for (int k = 0; k < 10; k++) {

			list.add(0);
		}



		for (int y = 0; y < board.length; y++) {

			if(!(board[i][y] == 0)) {

				list.set(board[i][y] , 1);

			}

		}

		for (int x = 0; x < board.length; x++) {

			if(!(board[x][j] == 0)) {

				list.set(board[x][j], 1);

			}

		}

		int k=0,l=0;

		if(i >= 0 && i<=2)
			k=0;

		else if(i >= 3 && i<= 5)
			k=3;
		else
			k=6;


		if(j >= 0 && j<= 2)
			l=0;

		else if(j >= 3 && j <= 5)
			l=3;
		else
			l=6;

		for (int x = k; x < k+3; x++) {

			for(int y=l ; y< l+3 ; y++) {

				if(board[x][y] != 0)
					list.set(board[x][y] , 1);

			}

		}



		for (int x= 0; x < list.size(); x++) {

			if(list.get(x).intValue() == 0)
				list.set(x, x);
			else
				list.set(x, 0);

		}


		return list;


	}

	public static boolean solve() {

		int i=0,j=0;

		boolean flag = false;

		ArrayList<Integer> list = new ArrayList<>();

		if(isFull()) {

			return true;
		}

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {

				if(board[x][y] == 0) {
					i=x;
					j=y;

					flag = true;
					break;

				}

			}

			if(flag)
				break;

		}

		list = possibleEntries(i, j);


		for (int x = 0; x < list.size(); x++) {

			if(list.get(x).intValue() !=0) {

				board[i][j] = list.get(x).intValue();

				int index = i*9+j;
				((TextField)Controller.list.get(index)).setText(board[i][j]+"");




				if(solve()) {

					return true;

				}


				board[i][j] = 0;

				// index = i*9+j;
				//((TextField)Controller.list.get(index)).setText("");

			}

		}



		return false;




	}

	public static void printBoard(int [][]board) {

		System.out.println("---------------------------");
		for (int x = 0; x < board.length; x++) {

			if(x ==  3 || x == 6)
				System.out.println("**********************");

			for (int y = 0; y < board.length; y++) {

				if(y==3 || y==6)
					System.out.print("*" + "    ");

				System.out.print(board[x][y] + "   ");				
			}
			System.out.println();


		}

	}


	public static void generate(String difficulty,int [][]board) {

		int noOfCells=0;

		if(difficulty.equalsIgnoreCase("Easy"))
			noOfCells = 20;
		else if(difficulty.equalsIgnoreCase("Meduim"))
			noOfCells = 15;
		else
			noOfCells = 10;


		Random random = new Random();

		int row=0,col=0;
		int max = 8,min= 0;
		int digitMin = 1 , digitMax = 9;
		int randomNumber = 0;

		for (int i = 0; i < noOfCells; i++) {

			row = random.nextInt((max - min) + 1) + min;
			col = random.nextInt((max - min) + 1) + min;
			randomNumber = random.nextInt((digitMax - digitMin) + 1) + digitMin;

			if (board[row][col] == 0 && noConflict(board, row, col, randomNumber)) {

				board[row][col] = randomNumber;

			} 
			else {

				i--;

			}


		}


	}

	private static boolean noConflict(int[][] board, int row, int col, int num) {

		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num) {
				return false;
			}
			if (board[i][col] == num) {
				return false;
			}
		}

		int k=0,l=0;

		if(row >= 0 && row<=2)
			k=0;

		else if(row >= 3 && row<= 5)
			k=3;
		else
			k=6;


		if(col >= 0 && col<= 2)
			l=0;

		else if(col >= 3 && col <= 5)
			l=3;
		else
			l=6;


		for (int p = k; p < k + 3; p++) {

			for (int q = l; q < l + 3; q++) {

				if (board[p][q] == num) {
					return false;
				}

			}

		}
		return true;
	}


	public static String isSafe(int r , int c , int n,int [][]board)
	{

		ArrayList<String> list  = new ArrayList<>();
		if( n < 0 || n > 9 )
		{
			return "Number";

		}
		else if(usedInRow(r,c,n,board)) {

			return "Row";

		}
		else if(usedInCol(r,c,n,board)) {

			return "Col";


		}
		else if(usedInBox(r,c,n,board) ) {

			return "box";
		}
		else {

			return null;

		}

	}


	public  static boolean usedInRow(int r , int c, int n , int [][]board)
	{ 
		for(int col=0 ; col<9 ; col++ )
		{
			if( col != c && board[r][col] == n )
			{
				return true;
			}
		}


		return false;
	}




	private static boolean usedInCol(int r,int c , int n,int [][]board)
	{
		for(int row=0 ; row < 9 ; row++ )
		{
			if( row != r && board[row][c] == n )
			{
				return true;
			}
		}

		return false;
	}

	private static boolean usedInBox(int i , int j , int n,int [][]board){
		

		int k=0,l=0;

		if(i >= 0 && i<=2)
			k=0;

		else if(i >= 3 && i<= 5)
			k=3;
		else
			k=6;


		if(j >= 0 && j<= 2)
			l=0;

		else if(j >= 3 && j <= 5)
			l=3;
		else
			l=6;
		
		for (int p = k; p < k + 3; p++) {

			for (int q = l; q < l + 3; q++) {

				if (board[p][q] == n && p!=i && q!=j) {
					return true;
				}

			}

		}
		return false;

	}



}
