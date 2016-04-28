public interface iBoardGame
{

	public boolean move(int playerNumber, int row, int col); // returns false if
																							// not a valid
																							// move
	public boolean isWin(); 						// returns true if any player has won.
	public boolean isDraw(); 						// returns true if it's a draw
	public void printOpeningMessage();
	public void printBoard();
}