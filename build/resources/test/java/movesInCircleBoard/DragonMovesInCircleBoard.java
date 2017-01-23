package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.Square;
import main.java.pieces.Piece;

public class DragonMovesInCircleBoard {

	private int counter = 5;
	private int fireLoader = 1;
	
	public void decreaseCounter(){
		counter--;
	}
	
	
	private void regularMove(Piece piece, ArrayList<Square> list, boolean ignoreKing) {
		int x = piece.getPozX(), y = piece.getPozY();
		for (int i = x - fireLoader; i <= x + fireLoader; i++) {
			for (int j = y - fireLoader; j <= y + fireLoader; j++) {
				if (!piece.pieceBehaviour.isout(i, j)) {// out of bounds
														// protection
					Square sq = piece.getSquares(i, j);
					if (piece.getSquare() == sq) {// if we're checking square on
													// which is King
						continue;
					} else {
						Square moveSq = piece.getSquares(i, j);
						if (moveSq.piece!=null && piece.pieceBehaviour.otherOwner(i, j)) {
								list.add(sq);}
						}
					}
				}
			}
		}


	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		regularMove(piece, list, ignoreKing);
		return list;
	}
}
