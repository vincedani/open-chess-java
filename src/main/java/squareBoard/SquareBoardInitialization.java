package main.java.squareBoard;

import main.java.board.Square;
import main.java.game.Player;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;

public class SquareBoardInitialization {
	private Square[][] squares;
	SquareBoard board;
	public Piece kingBlue;
	public Piece kingWhite;
	public Piece kingBlack;

	public SquareBoardInitialization(SquareBoard board) {
		this.board = board;
		setSquares(new Square[8][8]);// Initialization of 8x8 chessboard
		createSquares();
	}

	/**
	 * Method createSquares creates an array of 8x8 Squares
	 * 
	 */
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
	 * @param players
	 *            set of players for the game
	 */
	public void setPieces(Player[] players) {

		this.setFigures4NewGame(0, players[0], true);
		this.setPawns4NewGame(1, players[0]);
		this.setFigures4NewGame(7, players[1], false);
		this.setPawns4NewGame(6, players[1]);
	}

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
		// Rook
		getSquares()[0][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.Rook));
		getSquares()[7][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.Rook));
		// Knight
		getSquares()[1][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.Knight));
		getSquares()[6][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.Knight));
		// Bishop
		getSquares()[2][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.Bishop));
		getSquares()[5][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.Bishop));

		if (upsideDown) {
			getSquares()[4][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.Queen));
			getSquares()[3][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.King));

			if (player.getColor().equals(Player.colors.white)) {
				kingWhite = squares[3][i].getPiece();
			} else if (player.getColor().equals(Player.colors.black)) {
				kingBlack = squares[3][i].getPiece();
			} else if (player.getColor().equals(Player.colors.blue)) {
				kingBlue = squares[3][i].getPiece();
			}
		} else {
			getSquares()[3][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.Queen));
			getSquares()[4][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.King));

			if (player.getColor().equals(Player.colors.white)) {
				kingWhite = squares[4][i].getPiece();
			} else if (player.getColor().equals(Player.colors.black)) {
				kingBlack = squares[4][i].getPiece();
			} else if (player.getColor().equals(Player.colors.blue)) {
				kingBlue = squares[4][i].getPiece();
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

		for (int x = 0; x < 8; x++) {
			getSquares()[x][i].setPiece(PieceFactory.createSpecificPieceForSquareBoard(board, player, PieceType.Pawn));

		}
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public void setKing(Piece king) {
		if (king.getPlayer().getColor().equals(Player.colors.white)) {
			kingWhite = king;
		} else if (king.getPlayer().getColor().equals(Player.colors.black)) {
			kingBlack = king;
		} else if (king.getPlayer().getColor().equals(Player.colors.blue)) {
			kingBlue = king;
		}
	}

}