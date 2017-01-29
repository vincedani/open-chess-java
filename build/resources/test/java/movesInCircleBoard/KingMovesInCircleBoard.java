package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IKing;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory.PieceType;

public class KingMovesInCircleBoard implements IMove, IKing {

	private void regularMove(IChessboard board, Piece piece, ArrayList<Square> list, int x, int y, boolean ignoreKing) {

		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				int posi = i;
				if (posi < 0) {
					posi += 24;
				} else if (posi > 23) {
					posi -= 24;
				}
				if (!piece.isout(posi, j) && piece.checkPiece(posi, j) &&(ignoreKing || willBeSafeAfterMove(board, piece.getSquare(), board.getSquareFromIndexes(posi, j)))) {
					list.add(board.getSquareFromIndexes(posi, j));
				}

			}

		}
	}

	public ArrayList<Square> getMoves(IChessboard board, Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		regularMove(board, piece, list, piece.getPosX(), piece.getPosY(), ignoreKing);
		return list;
	}

	/**
	 * Method to check is the king is checked by an opponent
	 * 
	 * @param s
	 *            Square where is a king
	 * @return bool true if king is safe, else returns false
	 */
	public boolean isSafe(IChessboard board, Piece king, Square sq) {
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 6; j++) {
				Piece boardPiece = board.getSquareFromIndexes(i, j).getPiece();
				if (boardPiece != null && boardPiece.getPlayer() != king.getPlayer()
						&& !boardPiece.getType().equals(PieceType.Dragon)) {

					ArrayList<Square> pieceMoves = boardPiece.allMoves(board, true);
					if (pieceMoves.contains(king.getSquare())) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public boolean isChecked(IChessboard board, Piece king) {
		return !isSafe(board, king, king.getSquare());
	}

	/**
	 * Method to check will the king be safe after the move of the pieces in the
	 * given squares
	 * 
	 * @param sqIsHere
	 *            the original square of the piece
	 * @param sqWillBeThere
	 *            the future square of the piece
	 * @return boolean true if king is save, else returns false
	 */
	public boolean willBeSafeAfterMove(IChessboard board, Square sqIsHere, Square sqWillBeThere) {
		Piece king = board.getKing(sqIsHere.getPiece().getPlayer());
		Piece tmp = sqWillBeThere.getPiece();
		sqWillBeThere.setPiece(sqIsHere.getPiece()); // move without redraw
		sqIsHere.setPiece(null);
		boolean ret;

		ret = isSafe(board, king, king.getSquare());

		sqIsHere.setPiece(sqWillBeThere.getPiece());
		sqWillBeThere.setPiece(tmp);

		return ret;
	}

	public KingState isCheckmatedOrStalemated(IChessboard board, Piece king) {

		if (king.allMoves(board, false).isEmpty()) {
			Piece boardPiece;
			for (int i = 0; i < 24; ++i) {
				for (int j = 0; j < 6; ++j) {
					boardPiece = board.getSquareFromIndexes(i, j).getPiece();
					if (boardPiece != null && boardPiece.getPlayer() == king.getPlayer()
							&& !boardPiece.allMoves(board, false).isEmpty()) {
						return KingState.safe;
					}
				}
			}

			if (isChecked(board, king)) {
				return KingState.checkmate;
			} else {
				return KingState.stalemate;
			}
		} else {
			return KingState.safe;
		}
	}

}
