package main.java.pieces;

import main.java.board.IChessboard;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.squareBoard.SquareBoard;

/**
 * Class to validate the piece behavior in any kind of Chessboard.
 */
public class PieceBehaviour {

	private IChessboard chessboard;
	private Player player;

	public PieceBehaviour(IChessboard chessboard, Player player) {

		this.setChessboard(chessboard);
		this.setPlayer(player);
	}

	/**
	 * Check if the x or y indexes are out of bounds
	 * 
	 * @return true when out of bounds, else false
	 */
	public boolean isout(int x, int y) {
		if (chessboard instanceof SquareBoard) {
			if (x < 0 || x > 7 || y < 0 || y > 7) {
				return true;
			}
		} else if (chessboard instanceof CircleBoard) {
			if (x < 0 || x > 23 || y < 0 || y > 5) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if there is a piece in a given x and y indexes
	 * 
	 * @param x
	 *            x index on chessboard
	 * @param y
	 *            y index on chessboard
	 * @return true if the square is empty or the piece is from a different
	 *         player , false if the square contains a piece of the same player
	 *         or a King
	 */
	public boolean checkPiece(int x, int y) {
		Piece squarePiece = getSquares(x,y).piece;
		if (squarePiece != null && squarePiece.getName().equals("King")) {
			return false;
		}
		if (squarePiece == null || squarePiece.getPlayer() != this.getPlayer()){
			return true;
		}
		return false;
	}

	/**
	 * Check if piece has a different owner than the calling piece
	 * 
	 * @param x
	 *            x index on chessboard
	 * @param y
	 *            y index on chessboard
	 * @return true if owner(player) is different
	 */
	public boolean otherOwner(int x, int y) {
		Square sq = getChessboard().getSquares()[x][y];
		if (sq.piece == null) {
			return false;
		}
		if (this.getPlayer() != sq.piece.getPlayer()) {
			return true;
		}
		return false;
	}

	public Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}

	private IChessboard getChessboard() {
		return chessboard;
	}

	public Square getSquares(int i, int j) {
		Square square = getChessboard().getSquares()[i][j];
		return square;
	}

	private void setChessboard(IChessboard chessboard2) {
		this.chessboard = chessboard2;
	}

}
