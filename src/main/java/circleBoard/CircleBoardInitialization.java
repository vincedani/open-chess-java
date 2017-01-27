package main.java.circleBoard;

import main.java.board.Square;
import main.java.game.Player;
import main.java.pieces.Piece;
import main.java.pieces.PieceBehaviour;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;

public class CircleBoardInitialization {
	Square[][] squares;
	CircleBoard board;
	public Piece kingWhite;
	public Piece kingBlack;
	public Piece kingBlue;

	public CircleBoardInitialization(CircleBoard board) {
		this.board = board;
		squares = new Square[24][6];// Initialization of 8x8 board
		PieceBehaviour pieceBehaviour = new PieceBehaviour(board);
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
		squares[i][j].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, player, PieceType.Rook));
		squares[i+7][j].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, player, PieceType.Rook));

		// Knight
		squares[i + 1][j].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, player, PieceType.Knight));
		squares[i + 6][j].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, player, PieceType.Knight));

		// Bishop
		squares[i + 2][j].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, player, PieceType.Bishop));
		squares[i + 5][j].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, player, PieceType.Bishop));

		// THE QUEEN MOTHER OF DRAGONS
		squares[i + 4][j].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, player, PieceType.Queen));

		//King
		squares[i + 3][j].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, player, PieceType.King));
		
		if (player.getColor().equals(Player.colors.white)) {
			kingWhite =  squares[i + 3][j].getPiece();
		} else if (player.getColor().equals(Player.colors.black)) {
			kingBlack =  squares[i + 3][j].getPiece();
		} else if (player.getColor().equals(Player.colors.blue)) {
			kingBlue = squares[i + 3][j].getPiece();
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
			
			squares[i + k][j].setPiece(PieceFactory.createSpecificPieceForCircleBoard(board, player, PieceType.Pawn));
		}
	}

	public Square[][] getSquares() {
		return squares;
	}

}
