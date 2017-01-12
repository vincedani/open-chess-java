package main.java.circleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.movesInCircleBoard.BishopMovesInCircleBoard;
import main.java.movesInCircleBoard.KingMovesInCircleBoard;
import main.java.movesInCircleBoard.KnightMovesInCircleBoard;
import main.java.movesInCircleBoard.PawnMovesInCircleBoard;
import main.java.movesInCircleBoard.RookMovesInCircleBoard;
import main.java.pieces.Bishop;
import main.java.pieces.King;
import main.java.pieces.Knight;
import main.java.pieces.Pawn;
import main.java.pieces.PieceFactory;
import main.java.pieces.Queen;
import main.java.pieces.Rook;

public class CircleBoardInitialization {
	Square[][] squares;
	CircleBoard board;
	public King kingWhite;
	public King kingBlack;
	public King kingBlue;

	public CircleBoardInitialization(CircleBoard board) {
		this.board = board;
		squares = new Square[24][6];// Initialization of 8x8 board
		createSquares();
	}

	/**
	 * Method createSquares creates an array of 24x6 Squares
	 * 
	 */
	private void createSquares() {
		for (int i = 0; i < 24; i++) {
			for (int y = 0; y < 6; y++) {
				getSquares()[i][y] = new Square(i, y, null);
			}
		}
	}

	/**
	 * Method setPieces on begin of new game or loaded game
	 * 
	 * @param players
	 *            array of the players of the game
	 * 
	 */
	public void setPieces(Player[] players) {

		for (int i = 0; i < players.length; i++) {
			setFigures4NewGame(i * 8, 0, players[i]);
			setPawns4NewGame(i * 8, 1, players[i]);
		}
	}/*--endOf-setPieces(boolean upsideDown)--*/

	/**
	 * Method set Figures in row and store each King
	 * 
	 * @param i
	 *            row to start the positioning
	 * @param j
	 *            column where to set figures (Rook, Knight etc.)
	 * @param player
	 *            which is owner of pawns
	 */
	private void setFigures4NewGame(int i, int j, Player player) {

		// Rooks
		ArrayList<IMove> rookMoves = new ArrayList<>();
		rookMoves.add(new RookMovesInCircleBoard());
		squares[i][j].setPiece(new Rook(board, player, rookMoves));
		squares[i + 7][j].setPiece(new Rook(board, player, rookMoves));

		// Knight
		squares[i + 1][j].setPiece(PieceFactory.createKnightInCircleBoard(board, player));
		squares[i + 6][j].setPiece(PieceFactory.createKnightInCircleBoard(board, player));

		// Bishop
		squares[i + 2][j].setPiece(PieceFactory.createBishopInCircleBoard(board, player));
		squares[i + 5][j].setPiece(PieceFactory.createBishopInCircleBoard(board, player));

		// THE QUEEN MOTHER OF DRAGONS
		squares[i + 4][j].setPiece(PieceFactory.createQueenInCircleBoard(board, player));

		ArrayList<IMove> kingMoves = new ArrayList<>();
		kingMoves.add(new KingMovesInCircleBoard());
		if (player.getColor().equals(Player.colors.white)) {
			squares[i + 3][j].setPiece(kingWhite = new King(board, player, kingMoves));
		} else if (player.getColor().equals(Player.colors.black)) {
			squares[i + 3][j].setPiece(kingBlack = new King(board, player, kingMoves));
		} else if (player.getColor().equals(Player.colors.blue)) {
			squares[i + 3][j].setPiece(kingBlue = new King(board, player, kingMoves));
		}

	}

	/**
	 * method set Pawns in row
	 * 
	 * @param i
	 *            row to start the positioning
	 * @param j
	 *            column where to set pawns
	 * @param player
	 *            player which is owner of pawns
	 */
	private void setPawns4NewGame(int i, int j, Player player) {

		for (int k = 0; k < 8; k++) {
			ArrayList<IMove> pawnMoves = new ArrayList<>();
			pawnMoves.add(new PawnMovesInCircleBoard());
			squares[i + k][j].setPiece(new Pawn(board, player, pawnMoves));
		}
	}

	public Square[][] getSquares() {
		return squares;
	}

}
