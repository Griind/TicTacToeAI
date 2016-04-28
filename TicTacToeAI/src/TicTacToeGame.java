import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeGame implements iBoardGame
{

	// ***********************************************************************************
	// Final by Roman Svitukha.
	//
	// *********************************************************************************

	// declare a Board object here and call it gameBoard.
	Board gameBoard = new Board();

	/**
	 * method checks if square with passed address is empty and assigns new
	 * value according to the player number;
	 */
	@Override
	public boolean move(int playerNumber, int row, int col)
	{

		if (gameBoard.squares[row][col].equals(Board.VACANT_CHAR))
		{
			gameBoard.squares[row][col] = (playerNumber == 1)
					? Board.X_CHAR
					: Board.O_CHAR;
			return true;
		}
		return false;
	}

	/**
	 * method with all possibles winning combinations. if any lineCheck()
	 * returns true the return value of isWin() will be true;
	 */
	@Override
	public boolean isWin()
	{

		boolean winCheckReturn = (lineCheck(0, 0, 0, 1, 0, 2) ^ // row 0
													lineCheck(1, 0, 1, 1, 1, 2) ^ // row 1
													lineCheck(2, 0, 2, 1, 2, 2) ^ // row 2
													lineCheck(0, 0, 1, 0, 2, 0) ^ // col 0
													lineCheck(0, 1, 1, 1, 2, 1) ^ // col 1
													lineCheck(0, 2, 1, 2, 2, 2) ^ // col 2
													lineCheck(0, 0, 1, 1, 2, 2) ^ // diagonal
													lineCheck(0, 2, 1, 1, 2, 0)); // second diagonal

		return winCheckReturn;
	}

	/*
	 * method checks if squares with addresses passed are the same. 3 for one
	 * player -3 for the other;
	 */
	private boolean lineCheck(int row1, int col1, int row2, int col2, int row3,
			int col3)
	{
		// if there there is X&X&X count = 3; if O&O&O count = -3 else false;

		int count = 0;

		// First cell
		if (gameBoard.squares[row1][col1].equals(Board.X_CHAR))
		{
			count = 1;
		} else if (gameBoard.squares[row1][col1].equals(Board.O_CHAR))
		{
			count = -1;
		}

		// Second cell
		if (gameBoard.squares[row2][col2].equals(Board.X_CHAR))
		{
			if (count == 1)
			{ // cell1 is X_CHAR
				count = 2;
			} else
			{ // cell1 is O_CHAR or empty;
				return false;
			}
		} else if (gameBoard.squares[row2][col2].equals(Board.O_CHAR))
		{
			if (count == -1)
			{ // cell1 is O_CHAR
				count = -2;
			} else
			{ // cell1 is X_CHAR or empty;
				return false;
			}
		}

		// Third cell
		if (gameBoard.squares[row3][col3].equals(Board.X_CHAR))
		{
			if (count == 2)
			{ // cell1 and/or cell2 is X_CHAR
				count = 3;
			} else
			{ // cell1 and/or cell2 is O_CHAR or empty;
				return false;

			}
		} else if (gameBoard.squares[row3][col3].equals(Board.O_CHAR))
		{
			if (count == -2)
			{ // cell1 and/or cell2 is O_CHAR
				count = -3;
			} else
			{ // cell1 and/or cell2 is X_CHAR or empty;
				return false;
			}
		}

		boolean result = ((count == 3) || (count == -3)) ? true : false;

		return result;
	}

	/**
	 * System out println the opening message for the game program. For example
	 * "Welcome to The GAME!! Good Luck".
	 */
	@Override
	public void printOpeningMessage()
	{

		System.out.println("Final by Roman Svitukha");
		System.out
				.println("Welcome To Tic Tac Toe!!!     \n\n Player 1 is X, player 2 is O");
		System.out.println();
	}

	/**
	 * method to print current board;
	 */
	@Override
	public void printBoard()
	{

		for (int row = 0; row < gameBoard.squares.length; row++)
		{
			String printLine = "";
			for (int col = 0; col < gameBoard.squares[row].length; col++)
			{
				printLine += gameBoard.squares[row][col].toString() + " ";
			}
			System.out.println(printLine);
		}

		System.out.println();

	}
	/**
	 * method writes game result to a file.
	 * 
	 * @param fileName
	 */
	public void printBoard(String fileName)
	{
		// do the same thing that the printBoard method above does, but output
		// to a file instead of the console

		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));

			for (int row = 0; row < gameBoard.squares.length; row++)
			{
				String printLine = "";
				for (int col = 0; col < gameBoard.squares[row].length; col++)
				{
					printLine += gameBoard.squares[row][col].toString() + " ";
				}

				out.write(printLine + "\n");
			}

			out.close();
		} catch (IOException e)
		{
			System.out.println("Error writing file has occured.");
		}

	}
	/**
	 * if all gameBoard.squares are not Vacant, if they are, return true, if not
	 * return false.
	 */
	@Override
	public boolean isDraw()
	{

		for (int row = 0; row < gameBoard.squares.length; row++)
		{

			for (int col = 0; col < gameBoard.squares[row].length; col++)
			{
				if (gameBoard.squares[row][col].equals(Board.VACANT_CHAR))
				{
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * method to give user a select option between human player or Ai player;
	 * 
	 * @return returns integer;
	 */
	public int userSelection()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true)
		{
			try
			{
				System.out.println("(R)egular game or game with (A)I?");
				String input = sc.next();
				if (input.equalsIgnoreCase("R"))
				{
					return 0;
				} else if (input.equalsIgnoreCase("A"))
				{

					System.out.println("You have selected unbeatable Ai player."); 
					System.out.println("First turn (Y)ou or (A)i?");
					input = sc.next();
					if (input.equalsIgnoreCase("Y"))
					{
						return 2;
					} else if (input.equalsIgnoreCase("A"))
					{
						return 1;
					}
				}

			} catch (Exception e)
			{
				System.out.println("Please try again. ");
			}
		}
	}
	/**
	 * this method calls minmax() and returns string value using second and
	 * third number using array received from minmax();
	 * 
	 * @return string value from array of strings;
	 */
	public String move(int playerNumber)
	{
		String tempPlayer = (playerNumber == 1) ? Board.X_CHAR : Board.O_CHAR;
		try
		{
			int[] result = minmax(2, tempPlayer);

			String board[][] =
			{
			{"1", "2", "3"},
			{"4", "5", "6"},
			{"7", "8", "9"}};

			return board[result[1]][result[2]];

		} catch (Exception e)
		{
			System.out.println("there was an error in ai move method");

		}
		return "";
	}
	/**
	 * Implementing "minimax" algorithm;
	 * 
	 * @param depth
	 *            layers deep to check;
	 * @param player
	 *            current player to check. X or O.
	 * @return returns array with best sum, row and column for best move;
	 */
	private int[] minmax(int depth, String player)
	{
		ArrayList<int[]> nextMoves = getValidMoves();
		int bestsum = (player == Board.O_CHAR)
				? Integer.MIN_VALUE
				: Integer.MAX_VALUE;

		int currentsum;
		int bestRow = -1;
		int bestCol = -1;

		if (nextMoves.isEmpty() || depth == 0)
		{
			// Gameover or depth reached, evaluate sum
			bestsum = evaluate();
		} else
		{

			for (int[] move : nextMoves)
			{
				// Try this move for the current "player"
				gameBoard.squares[move[0]][move[1]] = player;
				if (player == Board.O_CHAR)
				{ // Board.O_CHAR (computer) is maximizing player
					currentsum = minmax(depth - 1, Board.X_CHAR)[0];
					if (currentsum > bestsum)
					{
						bestsum = currentsum;
						bestRow = move[0];
						bestCol = move[1];
					}
				} else
				{ // Board.X_CHAR is minimizing player
					currentsum = minmax(depth - 1, Board.O_CHAR)[0];
					if (currentsum < bestsum)
					{
						bestsum = currentsum;
						bestRow = move[0];
						bestCol = move[1];
					}
				}

				// Undo move
				gameBoard.squares[move[0]][move[1]] = Board.VACANT_CHAR;
			}
		}
		return new int[]
		{bestsum, bestRow, bestCol};
	}
	/**
	 * writes to an array all the empty squares using ArrayList;
	 * 
	 * @return an array;
	 */
	private ArrayList<int[]> getValidMoves()
	{
		ArrayList<int[]> validMoves = new ArrayList<int[]>();
		for (int row = 0; row < gameBoard.squares.length; row++)
		{

			for (int col = 0; col < gameBoard.squares[row].length; col++)
			{
				if (gameBoard.squares[row][col].equals(Board.VACANT_CHAR))
				{
					validMoves.add(new int[]
					{row, col});
				}
			}
		}
		return validMoves;

	}

	/**
	 * Evaluate sum for each of the 8 lines (3 rows, 3 columns, 2 diagonals);
	 * 
	 * @return sum;
	 */
	private int evaluate()
	{
		int sum = 0;

		sum += checkLine(0, 0, 0, 1, 0, 2); // row 0
		sum += checkLine(1, 0, 1, 1, 1, 2); // row 1
		sum += checkLine(2, 0, 2, 1, 2, 2); // row 2
		sum += checkLine(0, 0, 1, 0, 2, 0); // col 0
		sum += checkLine(0, 1, 1, 1, 2, 1); // col 1
		sum += checkLine(0, 2, 1, 2, 2, 2); // col 2
		sum += checkLine(0, 0, 1, 1, 2, 2); // diagonal
		sum += checkLine(0, 2, 1, 1, 2, 0); // second diagonal
		return sum;
	}
	/**
	 * evaluation function for the given line of 3 cells;
	 * 
	 * @param row1
	 * @param col1
	 * @param row2
	 * @param col2
	 * @param row3
	 * @param col3
	 * @return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer. -100, -10, -1
	 *         for 3-, 2-, 1-in-a-line for opponent. 0 otherwise
	 */
	private int checkLine(int row1, int col1, int row2, int col2, int row3,
			int col3)
	{
		int sum = 0;

		// First cell
		if (gameBoard.squares[row1][col1].equals(Board.O_CHAR))
		{
			sum = 1;
		} else if (gameBoard.squares[row1][col1].equals(Board.X_CHAR))
		{
			sum = -1;
		}

		// Second cell
		if (gameBoard.squares[row2][col2].equals(Board.O_CHAR))
		{
			if (sum == 1)
			{ // cell1 is Board.O_CHAR
				sum = 10;
			} else if (sum == -1)
			{ // cell1 is Board.X_CHAR
				return 0;
			} else
			{ // cell1 is empty
				sum = 1;
			}
		} else if (gameBoard.squares[row2][col2].equals(Board.X_CHAR))
		{
			if (sum == -1)
			{ // cell1 is Board.X_CHAR
				sum = -10;
			} else if (sum == 1)
			{ // cell1 is Board.O_CHAR
				return 0;
			} else
			{ // cell1 is empty
				sum = -1;
			}
		}

		// Third cell
		if (gameBoard.squares[row3][col3].equals(Board.O_CHAR))
		{
			if (sum > 0)
			{ // cell1 and/or cell2 is Board.O_CHAR
				sum *= 10;
			} else if (sum < 0)
			{ // cell1 and/or cell2 is Board.X_CHAR
				return 0;
			} else
			{ // cell1 and cell2 are empty
				sum = 1;
			}
		} else if (gameBoard.squares[row3][col3].equals(Board.X_CHAR))
		{
			if (sum < 0)
			{ // cell1 and/or cell2 is Board.X_CHAR
				sum *= 10;
			} else if (sum > 1)
			{ // cell1 and/or cell2 is Board.O_CHAR
				return 0;
			} else
			{ // cell1 and cell2 are empty
				sum = -1;
			}
		}
		return sum;
	}

}