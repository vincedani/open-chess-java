package jchess.board;

import jchess.game.Player;
import jchess.pieces.Bishop;
import jchess.pieces.King;
import jchess.pieces.Knight;
import jchess.pieces.Pawn;
import jchess.pieces.Queen;
import jchess.pieces.Rook;

public class CircleBoardInitialization {

	IChessboard chessboard;

	public static Square[][] initializeCircleBoard(IChessboard chessboard, Player[] players, String places) {

		Square[][] squares=createSquares(24, 6);
		return setPieces(squares,chessboard, places, players);
	}

	private static Square[][] createSquares(int nx, int ny) {
		Square[][] squares = new Square[nx][ny];
		for (int i = 0; i < nx; i++) {// create object for each square
			for (int j = 0; j < ny; j++) {
				squares[i][j] = new Square(i, j, null);
			}
		} // --endOf--create object for each square
		
		return squares;
	}

	/**
	 * Method setPieces on begin of new game or loaded game
	 * 
	 * @param places
	 *            string with pieces to set on chessboard
	 * @param plWhite
	 *            reference to white player
	 * @param plBlack
	 *            reference to black player
	 */
	public static Square[][] setPieces(Square[][] squares, IChessboard chessboard, String places, Player[] players) {

		if (places.equals("")) // if newGame
		{
			setPieces4NewGame(squares, chessboard, players);
			return squares;
		} else // if loadedGame
		{
			return null;
		}
	}/*--endOf-setPieces--*/

	/**
	 *
	 */
	private static void setPieces4NewGame(Square[][] squares, IChessboard chessboard, Player[] players) {
		for (int i = 0; i < players.length; i++) {
			setFigures4NewGame(squares, chessboard, i * 8, 0, players[i]);
			setPawns4NewGame(squares, chessboard, i * 8, 1, players[i]);
		}

	}/*--endOf-setPieces(boolean upsideDown)--*/

	/**
	 * method set Figures in row (and set Queen and King to right position)
	 * 
	 * @param i
	 *            column to start the positioning
	 * @param j
	 *            row where to set figures (Rook, Knight etc.)
	 * @param player
	 *            which is owner of pawns
	 */
	private static void setFigures4NewGame(Square[][] squares, IChessboard chessboard, int i, int j, Player player) {

		squares[i][j].setPiece(new Rook(chessboard, player));
		squares[i + 1][j].setPiece(new Bishop(chessboard, player));
		squares[i + 2][j].setPiece(new Knight(chessboard, player));
		squares[i + 3][j].setPiece(new King(chessboard, player));
		squares[i + 4][j].setPiece(new Queen(chessboard, player));
		squares[i + 5][j].setPiece(new Knight(chessboard, player));
		squares[i + 6][j].setPiece(new Bishop(chessboard, player));
		squares[i + 7][j].setPiece(new Rook(chessboard, player));

	}

	/**
	 * method set Pawns in row
	 * 
	 * @param i
	 *            column to start the positioning
	 * @param j
	 *            row where to set pawns
	 * @param player
	 *            player which is owner of pawns
	 */
	private static void setPawns4NewGame(Square[][] squares, IChessboard chessboard, int i, int j, Player player) {
		for (int k = 0; k < 8; k++) {
			squares[i + k][j].setPiece(new Pawn(chessboard, player));
		}
	}
}
