package main.java.squareBoard;

/**
 * Class to initialize the pieces in a SquareChessboard for a two person chess game. 
 */

import java.util.ArrayList;

import main.java.Constants;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.movesInSquareBoard.BishopMovesInSquareBoard;
import main.java.movesInSquareBoard.KingMovesInSquareBoard;
import main.java.movesInSquareBoard.KnightMovesInSquareBoard;
import main.java.movesInSquareBoard.PawnMovesInSquareBoard;
import main.java.movesInSquareBoard.RookMovesInSquareBoard;
import main.java.pieces.ConcretePieceFactory;
import main.java.pieces.King;
import main.java.pieces.PieceFactory;

public class SquareBoardInitialization {
	private Square[][] squares;
	boolean upsideDown;
	SquareBoard board;
	public King kingWhite;
	public King kingBlack;

	public SquareBoardInitialization(boolean upsideDown, SquareBoard board) {
		this.upsideDown = upsideDown;
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
	 * @param plWhite
	 *            reference to white player
	 * @param plBlack
	 *            reference to black player
	 */
	public void setPieces(Player plWhite, Player plBlack) {

		if (upsideDown) {
			this.setPieces4NewGame(true, plWhite, plBlack);
		} else {
			this.setPieces4NewGame(false, plWhite, plBlack);
		}

	}

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
        
		ConcretePieceFactory pieceFac = new ConcretePieceFactory();
		// Rook
		getSquares()[0][i].setPiece(pieceFac.GetPieceForSquareBoard(Constants.Symbols.Rook.toString(),Constants.Pieces.Rook.toString(), board, player/*PieceFactory.createRookInSquareBoard(board, player)*/));
		getSquares()[7][i].setPiece(pieceFac.GetPieceForSquareBoard(Constants.Symbols.Rook.toString(),Constants.Pieces.Rook.toString(), board, player/*PieceFactory.createRookInSquareBoard(board, player)*/));

		// Knight
		ArrayList<IMove> knightMoves = new ArrayList<>();
		knightMoves.add(new KnightMovesInSquareBoard());
		getSquares()[1][i].setPiece(pieceFac.GetPieceForSquareBoard(Constants.Symbols.Knight.toString(),Constants.Pieces.Knight.toString(), board, player/*PieceFactory.createKnightInSquareBoard(board, player)*/));
		getSquares()[6][i].setPiece(pieceFac.GetPieceForSquareBoard(Constants.Symbols.Knight.toString(),Constants.Pieces.Knight.toString(), board, player)/*PieceFactory.createKnightInSquareBoard(board, player)*/);

		// Bishop
		getSquares()[2][i].setPiece(pieceFac.GetPieceForSquareBoard(Constants.Symbols.Bishop.toString(),Constants.Pieces.Bishop.toString(), board, player)/*PieceFactory.createBishopInSquareBoard(board, player)*/);
		getSquares()[5][i].setPiece(pieceFac.GetPieceForSquareBoard(Constants.Symbols.Bishop.toString(),Constants.Pieces.Bishop.toString(), board, player)/*PieceFactory.createBishopInSquareBoard(board, player)*/);

		// THE QUEEN MOTHER OF DRAGONS
		ArrayList<IMove> queenMoves = new ArrayList<>();
		queenMoves.add(new RookMovesInSquareBoard());
		queenMoves.add(new BishopMovesInSquareBoard());

		ArrayList<IMove> kingMoves = new ArrayList<>();
		kingMoves.add(new KingMovesInSquareBoard());

		if (upsideDown) {
			getSquares()[4][i].setPiece(pieceFac.GetPieceForSquareBoard(Constants.Symbols.Queen.toString(),Constants.Pieces.Queen.toString(), board, player)/*PieceFactory.createQueenInSquareBoard(board, player)*/);

			if (player.getColor() == Player.colors.white) {

				getSquares()[3][i].setPiece(kingWhite = new King(board, player, kingMoves));
			} else {
				getSquares()[3][i].setPiece(kingBlack = new King(board, player, kingMoves));
			}
		} else {
			getSquares()[3][i].setPiece(pieceFac.GetPieceForSquareBoard(Constants.Symbols.Queen.toString(),Constants.Pieces.Queen.toString(), board, player)/*PieceFactory.createQueenInSquareBoard(board, player)*/);

			if (player.getColor() == Player.colors.white) {
				getSquares()[4][i].setPiece(kingWhite = new King(board, player, kingMoves));
			} else {
				getSquares()[4][i].setPiece(kingBlack = new King(board, player, kingMoves));
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
		ConcretePieceFactory pieceFac = new ConcretePieceFactory();
		if (i != 1 && i != 6) {
			System.out.println("error setting pawns etc.");
			return;
		}

		ArrayList<IMove> pawnMoves = new ArrayList<>();
		pawnMoves.add(new PawnMovesInSquareBoard());

		for (int x = 0; x < 8; x++) {
			getSquares()[x][i].setPiece(pieceFac.GetPieceForSquareBoard("",Constants.Pieces.Pawn.toString(), board, player)/*PieceFactory.createPawnInSquareBoard(board, player)*/);
		}
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

}