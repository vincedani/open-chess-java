package main.java.circleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.movesInCircleBoard.KnightMoves;
import main.java.movesInCircleBoard.PawnMoves;
import main.java.movesInCircleBoard.RookMoves;
import main.java.movesInCircleBoard.KingMoves;
import main.java.movesInCircleBoard.BishopMoves;
import main.java.pieces.Bishop;
import main.java.pieces.King;
import main.java.pieces.Knight;
import main.java.pieces.Pawn;
import main.java.pieces.Queen;
import main.java.pieces.Rook;

public class CircleBoardInitialization {
	Square[][] squares;
	CircleBoard board;
	public King kingWhite;
	public King kingBlack;
	public King kingBlue;

	public CircleBoardInitialization(CircleBoard board) {
		this.board=board;
		setSquares(new Square[24][6]);// Initialization of 8x8 board
		createSquares();
	}

	private void createSquares() {
		for (int i = 0; i < 24; i++) {// create object for each square
			for (int y = 0; y < 6; y++) {
				getSquares()[i][y] = new Square(i, y, null);
			}
		} // --endOf--create object for each square
	}

	/**
	 * Method setPieces on begin of new game or loaded game
	 * 
	 * @param places
	 *            string with pieces to set on board
	 * @param plWhite
	 *            reference to white player
	 * @param plBlack
	 *            reference to black player
	 */
	public void setPieces(String places, Player[] players) {

		
	this.setPieces4NewGame(players);
			
	}/*--endOf-setPieces--*/

	/**
	 *
	 */
	private void setPieces4NewGame(Player[] players) {

		for (int i = 0; i < players.length; i++) {
			setFigures4NewGame( i * 8, 0, players[i]);
			setPawns4NewGame(i * 8, 1, players[i]);
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
	private void setFigures4NewGame(int i, int j, Player player) {

		//Rooks
		ArrayList <IMove> rookMoves = new ArrayList<>();
		rookMoves.add(new RookMoves());
		squares[i][j].setPiece(new Rook(board, player, rookMoves));
		squares[i + 7][j].setPiece(new Rook(board, player, rookMoves));
		
		//Knight
		ArrayList <IMove> knightMoves = new ArrayList<>();
		knightMoves.add(new KnightMoves());
		squares[i + 1][j].setPiece(new Knight(board, player, knightMoves));
		squares[i + 6][j].setPiece(new Knight(board, player, knightMoves));
		
		//Bishop
		ArrayList <IMove> bishopMoves = new ArrayList<>();
		bishopMoves.add(new BishopMoves());
		squares[i + 2][j].setPiece(new Bishop(board, player, bishopMoves));
		squares[i + 5][j].setPiece(new Bishop(board, player, bishopMoves));
		
		//THE QUEEN MOTHER OF DRAGONS
		ArrayList <IMove> queenMoves = new ArrayList<>();
		queenMoves.add(new RookMoves());
		queenMoves.add(new BishopMoves());
		squares[i + 4][j].setPiece(new Queen(board, player, queenMoves));
		
		ArrayList <IMove> kingMoves = new ArrayList<>();
		kingMoves.add(new KingMoves());
		if (player.getColor().equals(Player.colors.white)) {
			squares[i + 3][j].setPiece(kingWhite=new King(board, player, kingMoves));
		} else if (player.getColor().equals(Player.colors.black)) {
			squares[i + 3][j].setPiece(kingBlack=new King(board, player, kingMoves));
		} else if (player.getColor().equals(Player.colors.blue)) {
			squares[i + 3][j].setPiece(kingBlue=new King(board, player, kingMoves));
		}
			
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
	private void setPawns4NewGame(int i, int j, Player player) {
		
		for (int k = 0; k < 8; k++) {
			ArrayList <IMove> pawnMoves = new ArrayList<>();
			pawnMoves.add(new PawnMoves());
			squares[i + k][j].setPiece(new Pawn(board, player, pawnMoves));
		}
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}
}
