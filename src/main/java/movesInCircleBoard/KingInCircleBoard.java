package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IKing;
import main.java.board.IMove;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.pieces.King;
import main.java.pieces.Piece;
import main.java.squareBoard.SquareBoard;

public class KingInCircleBoard implements IMove , IKing {

	public enum KingState {safe, checkmate, stalemate}
	
	private void regularMove(Piece piece1, ArrayList<Square> list, int x, int y, boolean ignoreKing) {
		King piece = (King) piece1;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!piece.pieceBehaviour.isout(i, j)) {// out of bounds
														// protection
					Square sq = piece.getSquare(i, j);
					if (piece.getSquare() == sq) {// if we're checking square on
													// which is King
						continue;
					} else {
						if (piece.pieceBehaviour.checkPiece(i, j)) {// if square
							//Check if will be checked
							if(piece.isSafe(sq)){
								list.add(sq);}

						}
					}
				}
			}
		}

	}

	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		int x = piece.getPosX(), y = piece.getPosY();
		regularMove(piece, list, x, y, ignoreKing);
		return list;
	}

	/**
	 * Method to check is the king is checked by an opponent
	 * 
	 * @param s
	 *            Square where is a king
	 * @return bool true if king is safe, else returns false
	 */
	public boolean isSafe(Piece king) 
	{
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 6; j++) {
				Piece boardPiece = king.getSquare(i, j).piece;
				if (boardPiece != null && boardPiece.getPlayer() != king.getPlayer()) {
					if(boardPiece.getName().equals("Dragon")){
						continue;
					}else{
						ArrayList<Square> pieceMoves = boardPiece.allMoves(true);
						if (pieceMoves.contains(king.getSquare())) {
							return false;
						}
					}}
				}
			}
		

		return true;
}
	
	@Override
	public boolean isChecked(Piece king) {
		return !isSafe(king);
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
	public boolean willBeSafeAfterMove(Piece king, Square sqIsHere, Square sqWillBeThere) {
		Piece tmp = sqWillBeThere.getPiece();
		sqWillBeThere.setPiece(sqIsHere.getPiece()); // move without redraw
		sqIsHere.setPiece(null);
		boolean ret;
		
		ret = isSafe(king);
		
		sqIsHere.setPiece(sqWillBeThere.getPiece());
		sqWillBeThere.setPiece(tmp);

		return ret;
	}
	

	@Override
	public KingState isCheckmatedOrStalemated(Piece king) {
			
		if (king.allMoves(false).isEmpty()) {
			Piece boardPiece;
			for (int i = 0; i < 24; ++i) {
				for (int j = 0; j < 6; ++j) {
					boardPiece = king.getSquare(i, j).piece;
					if (boardPiece != null && boardPiece.getPlayer() == king.getPlayer()
							&& king.allMovesSize(boardPiece, false) != 0) {
						return KingState.safe;
					}
				}
			}

			if (isChecked(king)) {
				return KingState.checkmate;
			} else {
				return KingState.stalemate;
			}
		} else {
			return KingState.safe;
		}
	}

}
