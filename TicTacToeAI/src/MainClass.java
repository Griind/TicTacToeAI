import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner mScan = new Scanner(System.in);

		final String TERMINATING_STRING = "E";

		final String PROGRAM_TERMINATED_MESSAGE = "GAME TERMINATED";

		String outputFile = "/Volumes/MacStorage/gameResults.txt"; 
		TicTacToeGame mTTTGame = new TicTacToeGame();

		
		int boardRows = mTTTGame.gameBoard.squares.length;
		int boardCols = mTTTGame.gameBoard.squares[0].length;

		mTTTGame.printOpeningMessage();
		int aiSelection = mTTTGame.userSelection();

		String userInput = "";
		int playerNumber = 1;

		while (true) {
			
			mTTTGame.printBoard();

			// ask user for move
			System.out.println("Enter 1-9 for move or (E)xit");

			switch (aiSelection) {
			/**
			 * switch statement for player selection. human, AI first or second
			 * move;
			 */
			case 0:
				userInput = mScan.next();
				break;

			case 1:
				userInput = (playerNumber == 1) ? mTTTGame.move(aiSelection) : mScan.next();
				break;
			case 2:
				userInput = (playerNumber == 1) ? mScan.next() : mTTTGame.move(aiSelection);
				break;
			}

			if (userInput.equals(TERMINATING_STRING)) {
				break;
			}
			try {
				int squareNumber = Integer.parseInt(userInput) - 1;
				if (squareNumber >= 0 && squareNumber < boardRows * boardCols) {
					if (mTTTGame.move(playerNumber, squareNumber / boardRows, squareNumber % boardCols)) {
						if (mTTTGame.isWin()) {
							System.out.println("Player #" + playerNumber + " WINS!!!");

							mTTTGame.printBoard();
							mTTTGame.printBoard(outputFile); // put YOUR path
																// and filename
																// here

							System.out.println(PROGRAM_TERMINATED_MESSAGE);
							return;

						}

						if (mTTTGame.isDraw()) {
							System.out.println("It's a DRAW!!!");

							mTTTGame.printBoard();
							mTTTGame.printBoard(outputFile); 

							System.out.println(PROGRAM_TERMINATED_MESSAGE);
							return;

						}

						playerNumber = (playerNumber == 1 ? 2 : 1);
					}
				}
			} catch (Exception x) {

			}

		}
		// this is an overloaded version of the printBoard method. print to a
		// disk file instead of the console.
		mTTTGame.printBoard(outputFile); 
		System.out.println(PROGRAM_TERMINATED_MESSAGE);
		mScan.close();
		return;

	}

}