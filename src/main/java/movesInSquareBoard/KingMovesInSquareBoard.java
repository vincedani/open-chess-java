package main.java.movesInSquareBoard;

import java.util.ArrayList;

import main.java.board.IChessboard;
import main.java.board.IKing;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.board.IKing.KingState;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory.PieceType;

public class KingMovesInSquareBoard implements IMove, IKing {

	public void regularMove(IChessboard board, Piece piece, ArrayList<Square> list, int x, int y, boolean ignoreKing) {
		
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!piece.isout(i, j) && piece.checkPiece(i, j) && (ignoreKing || willBeSafeAfterMove(board, piece.getSquare(), board.getSquareFromIndexes(i, j)))) {
					list.add(board.getSquareFromIndexes(i, j));

				}
			}
		}

	}

	public void castlingLeftMove(IChessboard board, Piece piece, ArrayList<Square> list, int x, int y) {
		boolean canCastling = true;

		Piece rook = board.getSquareFromIndexes(0, y).getPiece();
		if (!rook.wasMoved()) {
			for (int i = x - 1; i > 0; i--) {
				if (board.getSquareFromIndexes(i, y).getPiece() != null) {
					canCastling = false;
					break;
				}
			}

			Square sq = board.getSquareFromIndexes(x - 2, y);
			Square sq1 = board.getSquareFromIndexes(x - 1, y);
			if (canCastling && isSafe(board,piece, sq) && isSafe(board,piece, sq1)) {
				// can do castling when neither sq nor sq1 is checked
				list.add(sq);
			}
		}
	}

	public void castlingRightMove(IChessboard board, Piece piece, ArrayList<Square> list, int x, int y) {
		boolean canCastling = true;

		Piece rook = board.getSquareFromIndexes(7,y).getPiece();
		if (!rook.wasMoved()) {
			for (int i = x + 1; i < 7; i++) {// go
												// left
				if (board.getSquareFromIndexes(i,y).getPiece() != null) {
					canCastling = false;
					break;
				}
			}

			Square sq = board.getSquareFromIndexes(x + 2,y);
			Square sq1 = board.getSquareFromIndexes(x + 1,y);
			if (canCastling && isSafe(board, piece, sq) && isSafe(board, piece, sq1)) {
				// can do castling when neither sq nor sq1 is checked
				list.add(sq);
			}
		}
	}

	public ArrayList<Square> getMoves(IChessboard board, Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();

		int x = piece.getPosX(), y = piece.getPosY();
		regularMove(board, piece, list, x, y, ignoreKing);
		Piece king = piece;
		if (!king.wasMoved()) {
			// check if king was not moved before
			Piece tempPiece = board.getSquareFromIndexes(0, y).getPiece();
			if (tempPiece != null && tempPiece.getType().equals(PieceType.Rook)) {
				castlingLeftMove(board, king, list, x, y);
			}
			if (board.getSquareFromIndexes(7, y).getPiece() != null && tempPiece.getType().equals(PieceType.Rook)) {
				castlingLeftMove(board, king, list, x, y);
			}
		}

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
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece boardPiece = board.getSquareFromIndexes(i, j).getPiece();
				if (boardPiece != null && boardPiece.getPlayer() != king.getPlayer()) {
					ArrayList<Square> pieceMoves = boardPiece.allMoves(board, true);
					if (pieceMoves.contains(sq)) {
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
		Piece king = sqIsHere.getPiece().myKingAsPiece();
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
			for (int i = 0; i < 8; ++i) {
				for (int j = 0; j < 8; ++j) {
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
