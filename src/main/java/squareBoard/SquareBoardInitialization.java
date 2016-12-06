package main.java.squareBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.movesInSquareBoard.PawnMove;
import main.java.pieces.Bishop;
import main.java.pieces.King;
import main.java.pieces.Knight;
import main.java.pieces.Pawn;
import main.java.pieces.Queen;
import main.java.pieces.Rook;

public class SquareBoardInitialization {
	private Square[][] squares;
	boolean upsideDown;
	SquareBoard board;
	public King kingWhite;
	public King kingBlack;

	public SquareBoardInitialization(boolean upsideDown, SquareBoard board) {
		this.upsideDown = upsideDown;
		this.board=board;
		setSquares(new Square[8][8]);//initalization of 8x8 chessboard
		createSquares();
	}

	private void createSquares() {
		
		for (int i = 0; i < 8; i++) {// create object for each square
			for (int y = 0; y < 8; y++) {
				getSquares()[i][y] = new Square(i, y, null);
			}
		} // --endOf--create object for each square
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
	public void setPieces(String places, Player plWhite, Player plBlack) {

		if (places.equals("")) // if newGame
		{
			if (upsideDown) {
				this.setPieces4NewGame(true, plWhite, plBlack);
			} else {
				this.setPieces4NewGame(false, plWhite, plBlack);
			}

		} else // if loadedGame
		{
			return;
		}
	}/*--endOf-setPieces--*/

	/**
	 *
	 */
	private void setPieces4NewGame(boolean upsideDown, Player plWhite, Player plBlack) {

		/* WHITE PIECES */
		Player player = plBlack;
		Player player1 = plWhite;
		if (upsideDown) // if white on Top
		{
			player = plWhite;
			player1 = plBlack;
		}
		this.setFigures4NewGame(0, player, upsideDown);
		this.setPawns4NewGame(1, player);
		this.setFigures4NewGame(7, player1, upsideDown);
		this.setPawns4NewGame(6, player1);
	}/*--endOf-setPieces(boolean upsideDown)--*/

	/**
	 * method set Figures in row (and set Queen and King to right position)
	 * 
	 * @param i
	 *            row where to set figures (Rook, Knight etc.)
	 * @param player
	 *            which is owner of pawns
	 * @param upsideDown
	 *            if true white pieces will be on top of chessboard
	 */
	private void setFigures4NewGame(int i, Player player, boolean upsideDown) {

		if (i != 0 && i != 7) {
			System.out.println("error setting figures like rook etc.");
			return;
		} else if (i == 0) {
			player.setGoDown(true);
		}

		getSquares()[0][i].setPiece(new Rook(board, player));
		getSquares()[7][i].setPiece(new Rook(board, player));
		getSquares()[1][i].setPiece(new Knight(board, player));
		getSquares()[6][i].setPiece(new Knight(board, player));
		getSquares()[2][i].setPiece(new Bishop(board, player));
		getSquares()[5][i].setPiece(new Bishop(board, player));
		if (upsideDown) {
		getSquares()[4][i].setPiece(new Queen(board, player));
			if (player.getColor() == Player.colors.white) {
				getSquares()[3][i].setPiece(kingWhite = new King(board, player));
			} else {
				getSquares()[3][i].setPiece(kingBlack = new King(board, player));
			}
		} else {
			getSquares()[3][i].setPiece(new Queen(board,player));
			if (player.getColor() == Player.colors.white) {
				getSquares()[4][i].setPiece(kingWhite = new King(board, player));
			} else {
				getSquares()[4][i].setPiece(kingBlack = new King(board, player));
			}
		}
	}

	/**
	 * method set Pawns in row
	 * 
	 * @param i
	 *            row where to set pawns
	 * @param player
	 *            player which is owner of pawns
	 */
	private void setPawns4NewGame(int i, Player player) {
		if (i != 1 && i != 6) {
			System.out.println("error setting pawns etc.");
			return;
		}
		
		ArrayList <IMove> pawnMoves = new ArrayList<>();
		pawnMoves.add(new PawnMove());
		
		for (int x = 0; x < 8; x++) {
			getSquares()[x][i].setPiece(new Pawn(board, player, pawnMoves));
		}
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

}