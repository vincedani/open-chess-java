package main.java.pieces;

import main.java.board.IChessboard;
import main.java.board.IKing;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.Player;
import main.java.game.Settings.BoardType;
import main.java.squareBoard.SquareBoard;

/**
 * Class to validate the piece behavior in any kind of Chessboard.
 */
public class PieceBehaviour {

	private IChessboard chessboard;

	public PieceBehaviour(IChessboard chessboard) {

		this.setChessboard(chessboard);
	}

	/**
	 * Check if the x or y indexes are out of bounds
	 * 
	 * @param x
	 *            x-index
	 * @param y
	 *            y-index
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
	 * @param piece
	 *            the instance of the piece that calls the function
	 * @param i
	 *            x index on chessboard
	 * @param j
	 *            y index on chessboard
	 * @return true if the square is empty or the piece is from a different
	 *         player , false if the square contains a piece of the same player
	 *         or a King
	 */
	public boolean checkMyPiece(Piece piece, int i, int j) {
		Piece squarePiece = chessboard.getSquareFromIndexes(i, j).getPiece();

		if (squarePiece == null || squarePiece.getPlayer() != piece.getPlayer()) {
			return true;
		}
		return false;
	}

	/**
	 * Check if piece has a different owner than the calling piece
	 * @param piece
	 *            the instance of the piece that calls the function
	 * 
	 * @param i
	 *            x index on chessboard
	 * @param j
	 *            y index on chessboard
	 * @return true if owner(player) is different
	 */
	public boolean otherOwner(Piece piece, int i, int j) {
		Square sq = chessboard.getSquareFromIndexes(i, j);
		if (sq.getPiece() == null) {
			return false;
		}
		if (piece.getPlayer() != sq.getPiece().getPlayer()) {
			return true;
		}
		return false;
	}

	public Square getSquare(int i, int j) {
		return chessboard.getSquareFromIndexes(i, j);
	}

	private void setChessboard(IChessboard chessboard2) {
		this.chessboard = chessboard2;
	}

	public IKing getKing(Player player) {
		return (IKing) this.chessboard.getKing(player).getMoveBehaviour();
	}

	public Piece getKingAsPiece(Player player) {
		return this.chessboard.getKing(player);
	}

	public BoardType getChessboardType() {
		if (chessboard instanceof CircleBoard)
			return BoardType.circleBoard;
		else if (chessboard instanceof SquareBoard)
			return BoardType.squareBoard;
		return null;
	}

	public IChessboard getChessboard() {
		return chessboard;
	}
}
