package main.java.circleBoard;

import java.util.ArrayList;

import main.java.Constants;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.game.Player;
import main.java.movesInCircleBoard.KingMovesInCircleBoard;
import main.java.pieces.ConcretePieceFactory;
import main.java.pieces.King;
import main.java.pieces.PieceFactory;

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
        
		ConcretePieceFactory piceFac = new ConcretePieceFactory();
		// Rooks
		squares[i][j].setPiece(piceFac.GetPieceForCircleBoard(Constants.Symbols.Rook.toString(),Constants.Pieces.Rook.toString(), board, player));/*PieceFactory.createRookInCircleBoard(board, player));*/
		squares[i+7][j].setPiece(piceFac.GetPieceForCircleBoard(Constants.Symbols.Rook.toString(),Constants.Pieces.Rook.toString(), board, player));/*PieceFactory.createRookInCircleBoard(board, player));*/

		// Knight
		squares[i + 1][j].setPiece(piceFac.GetPieceForCircleBoard(Constants.Symbols.Knight.toString(),Constants.Pieces.Knight.toString(), board, player));/*PieceFactory.createKnightInCircleBoard(board, player));*/
		squares[i + 6][j].setPiece(piceFac.GetPieceForCircleBoard(Constants.Symbols.Knight.toString(),Constants.Pieces.Knight.toString(), board, player));/*PieceFactory.createKnightInCircleBoard(board, player));*/

		// Bishop
		squares[i + 2][j].setPiece(piceFac.GetPieceForCircleBoard(Constants.Symbols.Bishop.toString(),Constants.Pieces.Bishop.toString(), board, player)/*PieceFactory.createBishopInCircleBoard(board, player)*/);
		squares[i + 5][j].setPiece(piceFac.GetPieceForCircleBoard(Constants.Symbols.Bishop.toString(),Constants.Pieces.Bishop.toString(), board, player)/*PieceFactory.createBishopInCircleBoard(board, player)*/);

		// THE QUEEN MOTHER OF DRAGONS
		squares[i + 4][j].setPiece(piceFac.GetPieceForCircleBoard(Constants.Symbols.Queen.toString(),Constants.Pieces.Queen.toString(), board, player)/*PieceFactory.createQueenInCircleBoard(board, player)*/);

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

		ConcretePieceFactory piceFac = new ConcretePieceFactory();
		
		for (int k = 0; k < 8; k++) {
			
			squares[i + k][j].setPiece(piceFac.GetPieceForCircleBoard("",Constants.Pieces.Pawn.toString(), board, player)/*PieceFactory.createPawnInCircleBoard(board, player)*/);
		}
	}

	public Square[][] getSquares() {
		return squares;
	}

}
